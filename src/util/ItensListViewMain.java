package util;

/**
 * Classe que representa o objeto item da tela Main.
 * 
 * @author fernando.gomes
 *
 */
public class ItensListViewMain
{
   /** CheckBox para ativar ou desativar*/
   public String state;
   /** String para o textView que indicara o Texto com o nome*/
   public String name;
   /** String para o textView que indicara o Horario da ultima comunicacao*/
   public String last_comm;

   public ItensListViewMain( String state, String name, String last_comm )
   {
      this.state = state;
      this.name = name;
      this.last_comm = last_comm;
   }

   public String getState()
   {
      return state;
   }

   public void setState( String state )
   {
      this.state = state;
   }

   public String getName()
   {
      return name;
   }

   public void setName( String name )
   {
      this.name = name;
   }

   public String getLast_comm()
   {
      return last_comm;
   }

   public void setLast_comm( String last_comm )
   {
      this.last_comm = last_comm;
   }
}
