package db.fr.sqllourd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/*
 * Classe Helper pour la gestion de l'ouverture de la BD
 * La connexion a la BD en somme ou la creation et connexion si elle n'existe pas
 * Methodes : constructeur, onCreate, onUpgrade
 */

// -------------------------------------
public class GestionnaireOuvertureSQLite extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "cours.db";

    private static final String DROP_TABLE_PAYS = "DROP TABLE IF EXISTS pays";
    private static final String CREATE_TABLE_PAYS = "CREATE TABLE IF NOT EXISTS pays(id_pays INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nom_pays VARCHAR(50) NOT NULL UNIQUE, iso2 VARCHAR(2) NOT NULL UNIQUE)";

    // --- Constructeur
    // -------------------------------
    public GestionnaireOuvertureSQLite(Context contexte, CursorFactory fabrique) {
        // --- Cree la BD si elle n'existe pas
        // --- et de ce fait execute le code de onCreate()
        // --- Se connecte si elle existe
        super(contexte, DB_NAME, fabrique, DB_VERSION);
    } /// GestionnaireOuvertureSQLite()

    @Override
    // -----------------
    public void onCreate(SQLiteDatabase abd) {
        // --- Creation de la table pays
        abd.execSQL(CREATE_TABLE_PAYS);

        /*
         * Pour les tests
         */
        // --- Remplissage de la table pays
        abd.execSQL("INSERT INTO pays(nom_pays, iso2) VALUES('France', '01')");
        abd.execSQL("INSERT INTO pays(nom_pays, iso2) VALUES('ESPAGNE', '02')");
        abd.execSQL("INSERT INTO pays(nom_pays, iso2) VALUES('ITALIE', '03')");
    } /// onCreate()

    @Override
    // ------------------
    public void onUpgrade(SQLiteDatabase abd, int ancienneVersion, int nouvelleVersion) {
        // --- Suppression de la table puis appel a la creation des tables
        abd.execSQL(DROP_TABLE_PAYS);
        onCreate(abd);
    } /// onUpgrade()

} /// class GestionnaireOuvertureSQLite