package com.fgomes.activities;

import java.util.Date;

import com.fgomes.objects.Directory;
import com.fgomes.refreshftp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import util.Consts;
import util.DbHelper;

public class RegisterActivity extends Activity
{

   private EditText etName;
   private EditText etIpHost;
   private EditText etPort;
   private EditText etUser;
   private EditText etPassword;
   private EditText etDirFtp;
   private EditText etDirLocal;
   private DbHelper m_dbh = new DbHelper( this );

   @Override
   protected void onCreate( Bundle p_savedInstanceState )
   {
      super.onCreate( p_savedInstanceState );
      setContentView( R.layout.activity_register_dir );

      etName = ( EditText )findViewById( R.id.etName );
      etIpHost = ( EditText )findViewById( R.id.etIpHost );
      etPort = ( EditText )findViewById( R.id.etPort );
      etUser = ( EditText )findViewById( R.id.etUser );
      etPassword = ( EditText )findViewById( R.id.etPass );
      etDirFtp = ( EditText )findViewById( R.id.etDirInFTP );
      etDirLocal = ( EditText )findViewById( R.id.etDirLocal );
   }

   @Override
   public boolean onCreateOptionsMenu( Menu p_menu )
   {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate( R.menu.main, p_menu );
      return true;
   }

   @Override
   public boolean onOptionsItemSelected( MenuItem p_item )
   {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = p_item.getItemId();
      if ( id == R.id.action_settings )
      {
         return true;
      }
      return super.onOptionsItemSelected( p_item );
   }

   public void evtRegister( View p_view )
   {
      Directory dir = new Directory();

      dir.setName( etName.getText().toString() );
      dir.setIp_host( etIpHost.getText().toString() );
      dir.setPort( etPort.getText().toString() );
      dir.setUser( etUser.getText().toString() );
      dir.setPassword( etPassword.getText().toString() );
      dir.setDir_ftp( etDirFtp.getText().toString() );
      dir.setDir_local( etDirLocal.getText().toString() );
      dir.setState( Consts.ValuesState.DISABLED );
      dir.setLast_comm( new Date( System.currentTimeMillis() ) );

      if ( m_dbh.insertDirectory( dir ) )
      {
         Toast.makeText( getApplicationContext(), "Sucesso!", Toast.LENGTH_SHORT ).show();
         Intent intent = new Intent( getApplicationContext(), MainActivity.class );
         startActivity( intent );

      }
      else
      {
         Toast.makeText( getApplicationContext(), "Error!", Toast.LENGTH_SHORT ).show();
      }
   }

}
