package util;

import java.util.ArrayList;
import java.util.List;

import com.fgomes.objects.Directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe responsavel por conectar no bd do ATMobile Launcher.
 *
 * Created by fernando.gomes on 21/11/2017.
 */
public class DbHelper extends SQLiteOpenHelper
{
   /**
    * Constante com o nome do banco de Dados.
    */
   private static final String DATABASE_NAME = "refreshftp";

   /**
    * Constante com a versao do Banco de Dados.
    */
   private static final int DATABASE_VERSION = 1;

   /**
    * Array de string com as querys para criar as tabelas do banco de dados.
    */
   private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
      "CREATE TABLE Directory(name CHAR(15) NOT NULL, ip_host CHAR(30) NOT NULL, port CHAR(6), user CHAR(20) NOT NULL, password CHAR(20) NOT NULL, dir_ftp CHAR(40) NOT NULL, dir_local CHAR(40) NOT NULL, state CHAR(1) NOT NULL, last_comm CHAR(20));"
   };

   /**
    * Construtor padrao que recebe um contexto.
    *
    * @param p_context
    */
   public DbHelper( Context p_context )
   {
      super( p_context, DATABASE_NAME, null, DATABASE_VERSION );
   }

   /**
    * Metodo onCreate para criar as tabelas no banco de dados.
    */
   @Override
   public void onCreate( SQLiteDatabase p_db )
   {
      String SqlDirectory =
         "CREATE TABLE Directory(name CHAR(15) NOT NULL, ip_host CHAR(30) NOT NULL, port CHAR(6), user CHAR(20) NOT NULL, password CHAR(20) NOT NULL, dir_ftp CHAR(40) NOT NULL, dir_local CHAR(40) NOT NULL, state CHAR(1) NOT NULL, last_comm CHAR(20));";
      p_db.execSQL( SqlDirectory );
   }

   /**
    * Metodo que atualiza o banco de dados.
    */
   @Override
   public void onUpgrade( SQLiteDatabase p_db, int p_oldVersion, int p_newVersion )
   {
      String SqlDropTableDirectory = "DROP TABLE Directory";
      p_db.execSQL( SqlDropTableDirectory );
      onCreate( p_db );
   }

   /**
    * Method that inserts a directory into the database.
    *
    * @param p_directory Directory.
    */
   public boolean insertDirectory( Directory p_directory )
   {
      boolean result = false;

      try
      {
         SQLiteDatabase db = getWritableDatabase();

         ContentValues cv = new ContentValues();

         cv.put( "name", p_directory.getName() );
         cv.put( "ip_host", p_directory.getIp_host() );
         cv.put( "port", p_directory.getPort() );
         cv.put( "user", p_directory.getUser() );
         cv.put( "password", p_directory.getPassword() );
         cv.put( "dir_ftp", p_directory.getDir_ftp() );
         cv.put( "dir_local", p_directory.getDir_ftp() );
         cv.put( "state", p_directory.getState() );
         cv.put( "last_comm", p_directory.getLast_comm() );

         db.insert( "Directory", null, cv );
         db.close();
         result = true;
      }
      catch ( Exception p_e )
      {
         p_e.printStackTrace();
      }
      return result;
   }

   /**
    * Metodo que seleciona a permissao de um determinado App.
    *
    * @param p_name_dir nome do directory que deseja buscar.
    *
    * @return permissionApp retorna a permissao do App pesquisado.
    */
   public Directory selectDirectoryForName( String p_name_dir )
   {
      Directory dir = new Directory();

      SQLiteDatabase db = getReadableDatabase();

      String SqlSelectOneAppInfo = "SELECT * FROM Directory WHERE name = '" + p_name_dir + "';";

      Cursor c = db.rawQuery( SqlSelectOneAppInfo, null );

      if ( c.moveToFirst() )
      {
         dir.setName( c.getString( 0 ) );
         dir.setIp_host( c.getString( 1 ) );
         dir.setPort( c.getString( 2 ) );
         dir.setUser( c.getString( 3 ) );
         dir.setPassword( c.getString( 4 ) );
         dir.setDir_ftp( c.getString( 5 ) );
         dir.setDir_local( c.getString( 6 ) );
         dir.setState( c.getString( 7 ) );
         dir.setLast_comm( c.getString( 8 ) );
      }
      c.close();

      return dir;
   }

   /*
    * Method that lists all directories.
    *
    * @return listDirectories return the list whit all the object.
    */
   public List<Directory> selectAllDirectory()
   {
      List<Directory> listDir = new ArrayList<Directory>();

      SQLiteDatabase db = getReadableDatabase();

      String SqlSelectListDirectory = "SELECT * FROM Directory";

      Cursor c = db.rawQuery( SqlSelectListDirectory, null );

      if ( c.moveToFirst() )
      {
         do
         {
            Directory dir = new Directory();

            dir.setName( c.getString( 0 ) );
            dir.setIp_host( c.getString( 1 ) );
            dir.setPort( c.getString( 2 ) );
            dir.setUser( c.getString( 3 ) );
            dir.setPassword( c.getString( 4 ) );
            dir.setDir_ftp( c.getString( 5 ) );
            dir.setDir_local( c.getString( 6 ) );
            dir.setState( c.getString( 7 ) );
            dir.setLast_comm( c.getString( 8 ) );

            listDir.add( dir );
         } while ( c.moveToNext() );
      }
      db.close();

      return listDir;
   }

   /**
    * Metodo que altera as senhas de configuracao do Launcher ATMobile.
    *
    * @param p_directory new date of object.
    *
    * @param p_name name of object.
    */
   public boolean updateDirectory( Directory p_directory, String p_name )
   {
      boolean result = false;
      try
      {
         Directory dir = new Directory();

         dir = p_directory;

         SQLiteDatabase db = getWritableDatabase();

         String SqlUpdatePassword = "UPDATE Directory SET name  = " + dir.getName() + ", ip_host = " +
            dir.getIp_host() +
            ", port = " + dir.getPort() + ", user = " + dir.getUser() + ", password = " + dir.getPassword() +
            ", dir_ftp = " + dir.getDir_ftp() + ", dir_local = " + dir.getDir_local() + ", state = " + dir.getState() +
            " WHERE name = " + p_name + ";";

         db.execSQL( SqlUpdatePassword );

         db.close();

         result = true;
      }
      catch ( Exception p_e )
      {
         p_e.printStackTrace();
      }
      return result;
   }

   /**
    * Metodo que faz uma limpeza nas tabelas do banco de dados.
    *
    * @return true / false.
    */
   public boolean dropDB()
   {
      boolean confirmDeleteTable = false;
      try
      {
         SQLiteDatabase db = getWritableDatabase();

         String SqlDropDbDirectory = "DELETE FROM Directory";

         db.execSQL( SqlDropDbDirectory );

         db.close();
         confirmDeleteTable = true;
      }
      catch ( Exception p_e )
      {
         p_e.printStackTrace();
      }
      return confirmDeleteTable;
   }
}
