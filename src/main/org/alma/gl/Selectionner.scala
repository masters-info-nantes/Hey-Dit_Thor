package org.alma.gl


/**
 * selectionne une partie du texte
 */
class Selectionner (w : Workspace, deb :Int , fin : Int) extends Command(w) {
  
   private var curseur : DoubleCursor = null
  // si le curseur de debut est au dela de la fin du texte on le 
  //place juste avant la fin et celui de fin a la fin
  if(deb>(work.buffer.getZone().length()-1)){
    curseur = new DoubleCursor(work.buffer.getZone().length()-1,work.buffer.getZone().length())
  }else if(fin<deb){
    curseur = new DoubleCursor(deb,deb+1)
  }else if(fin>work.buffer.getZone().length()){
    //fin defini au dela de la taille du texte
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