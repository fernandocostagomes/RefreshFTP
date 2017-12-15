package com.fgomes.activities;

import java.util.ArrayList;
import java.util.List;

import com.fgomes.objects.Directory;
import com.fgomes.refreshftp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import util.Consts;
import util.DbHelper;
import util.ItensListViewMain;

public class MainActivity extends Activity
{
   private List<Directory> m_listDir;
   ListView m_lvMain;
   private List<ItensListViewMain> m_listItens;

   private void loadListDirectory()
   {
      DbHelper m_dbh = new DbHelper( this );
      m_listDir = m_dbh.selectAllDirectory();
      m_listItens = new ArrayList<ItensListViewMain>();

      // Strings para adicionar na lista de itens para adicionar no adapter.
      String text1 = "";
      String text2 = "";
      String text3 = "";

      for ( int i = 0; i < m_listDir.size(); i++ )
      {
         text1 = m_listDir.get( i ).getState().toString();
         text2 = m_listDir.get( i ).getName().toString();
         text3 = m_listDir.get( i ).getState().toString();

         ItensListViewMain item = new ItensListViewMain( text1, text2, text3 );
         m_listItens.add( item );
      }

   }

   private void createListView()
   {
      ArrayAdapter<ItensListViewMain> adapter = new ArrayAdapter<ItensListViewMain>( this,
         R.layout.activity_itens_main, m_listItens )
      {
         @Override
         public View getView( int p_position, View p_convertView, ViewGroup p_parent )
         {
            // Use view holder patern to better performance with list view.
            ViewHolderItem viewHolder = null;

            if ( p_convertView == null )
            {
               p_convertView = getLayoutInflater().inflate( R.layout.activity_itens_main, p_parent,
                  false );

               viewHolder = new ViewHolderItem();

               viewHolder.checkBoxState = ( CheckBox )p_convertView.findViewById( R.id.cbState );
               viewHolder.textViewName = ( TextView )p_convertView.findViewById( R.id.tvName );
               viewHolder.textViewLastComm = ( TextView )p_convertView.findViewById( R.id.tvLastComm );
               viewHolder.buttonEdit = ( Button )p_convertView.findViewById( R.id.btEdit );

               // store holder with view.
               p_convertView.setTag( viewHolder );
            }
            // get saved holder
            else
            {
               viewHolder = ( ViewHolderItem )p_convertView.getTag();
            }
            ItensListViewMain itemL = m_listItens.get( p_position );

            // Configurando o checkbox.
            if ( ( itemL.state ).equals( Consts.ValuesState.ENABLED ) )
            {
               viewHolder.checkBoxState.setChecked( true );
            }

            // Tratando o textview com o nome.
            viewHolder.textViewName.setText( itemL.name );

            // Tratando o textview com a data.
            String lastcomm = itemL.last_comm.toString();
            viewHolder.textViewLastComm.setText( lastcomm );

            /**
             *  Evento de toque em um item da lista.
             */
            viewHolder.checkBoxState.setOnCheckedChangeListener( new OnCheckedChangeListener()
            {
               @Override
               public void onCheckedChanged( CompoundButton p_buttonView, boolean p_isChecked )
               {
                  // Se o item estiver desmarcado, entrara nas validacoes de adicionar na lista.
                  if ( p_isChecked )
                  {
                     Toast.makeText( getApplicationContext(), "Marcando o check", Toast.LENGTH_SHORT ).show();
                  }
                  if ( !p_isChecked )
                  {
                     Toast.makeText( getApplicationContext(), "Tirando o check", Toast.LENGTH_SHORT ).show();
                  }
               }
            } );

            return p_convertView;
         }

         final class ViewHolderItem
         {
            CheckBox checkBoxState;
            TextView textViewName;
            TextView textViewLastComm;
            Button buttonEdit;
         }
      };
      m_lvMain.setAdapter( adapter );

   }

   private void initProcess()
   {
      loadListDirectory();
      createListView();
   }

   @Override
   protected void onCreate( Bundle p_savedInstanceState )
   {
      super.onCreate( p_savedInstanceState );
      setContentView( R.layout.activity_main );

      m_lvMain = ( ListView )findViewById( R.id.lvMain );

      initProcess();
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

   public void evtAddNewDir( View p_view )
   {
      Intent intent = new Intent( getApplicationContext(), RegisterActivity.class );
      startActivity( intent );

   }
}
