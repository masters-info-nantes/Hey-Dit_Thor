package org.alma.gl


/**
 * @author E120404Z
 */
class Selectionner (w : Workspace, deb :Int , fin : Int) extends Command(w) {
  
   private var curseur : DoubleCursor = null
   
  if(deb>(work.buffer.getZone().length()-1)){
    curseur = new DoubleCursor(work.buffer.getZone().length()-1,work.buffer.getZone().length())
  }else if(fin<deb){
    curseur = new DoubleCursor(deb,deb+1)
  }else if(fin>work.buffer.getZone().length()){
    curseur = new DoubleCursor(deb,work.buffer.getZone().length())
  }else{
    curseur = new DoubleCursor(deb,fin)
  }
  
    override def execute() : Unit={
      var x : Int = curseur.getPosDeb
      var str : String = ""
      while(x != (curseur.getPosFin )){
        str = str + w.buffer.getZone.charAt(x)
        x = x+1
      } 
      w.selection.setContenu(str)
      w.selection.setCurseur(curseur)
     // w.str = str
      //return str
   }
  

  
   def deplaceCurseurFin(n: Integer) : Unit={
    if(n> w.buffer.getZone.length()){
      curseur.setPosFin(w.buffer.getZone.length)
    }
    else
      {curseur.setPosFin(n)}
    execute
  }
    
   def deplaceCurseurDeb(n: Integer) : Unit={
    if(n>0)
      {curseur.setPosDeb(n)}
    else
      {curseur.Select(0, curseur.getPosFin())}
    
    if (curseur.getPosDeb > curseur.getPosFin){
      curseur.setPosFin(curseur.getPosDeb)
    }
    execute
   }
   
   def getCurseur() : DoubleCursor={
     return curseur
   }
   
  
}