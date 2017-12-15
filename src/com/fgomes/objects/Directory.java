package com.fgomes.objects;

public class Directory
{
   private String name;
   private String ip_host;
   private String port;
   private String user;
   private String password;
   private String dir_ftp;
   private String dir_local;
   private String state;
   private String last_comm;

   public Directory()
   {
      super();
   }

   public Directory( String name, String ip_host, String port, String user, String password, String dir_ftp,
      String dir_local, String state, String last_comm )
   {
      super();
      this.name = name;
      this.ip_host = ip_host;
      this.port = port;
      this.user = user;
      this.password = password;
      this.dir_ftp = dir_ftp;
      this.dir_local = dir_local;
      this.state = state;
      this.last_comm = last_comm;
   }

   public String getName()
   {
      return name;
   }

   public void setName( String name )
   {
      this.name = name;
   }

   public String getIp_host()
   {
      return ip_host;
   }

   public void setIp_host( String ip_host )
   {
      this.ip_host = ip_host;
   }

   public String getPort()
   {
      return port;
   }

   public void setPort( String port )
   {
      this.port = port;
   }

   public String getUser()
   {
      return user;
   }

   public void setUser( String user )
   {
      this.user = user;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword( String password )
   {
      this.password = password;
   }

   public String getDir_ftp()
   {
      return dir_ftp;
   }

   public void setDir_ftp( String dir_ftp )
   {
      this.dir_ftp = dir_ftp;
   }

   public String getDir_local()
   {
      return dir_local;
   }

   public void setDir_local( String dir_local )
   {
      this.dir_local = dir_local;
   }

   public String getState()
   {
      return state;
   }

   public void setState( String state )
   {
      this.state = state;
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
