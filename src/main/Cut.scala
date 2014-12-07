

/**
 * @author E120404Z
 */
class Cut (w : Workspace) extends Command(w) {
  
   override def execute{
    if(!w.selection.isEmpty)
    {
      work.clipBoard.setContenu(work.selection.getContenu)
      var str : String = ""
      var temp : Int= work.selection.getCurseur.getPosFin()
      
      /* recuperation de la chaine presente avant le debut de la selection */
      for(x <- 0 to (work.selection.getCurseur.getPosDeb()-1)){
        str = str + work.buffer.getZone().charAt(x)
      }
     /*recuperation de la chaine presente apres la fin de la selection */
      while(temp != (work.buffer.getZone().length()))
      {
        str = str + work.buffer.getZone().charAt(temp)
        temp = temp + 1 
      }
      work.buffer.setZone(str) 
      work.buffer.deplaceCurseurCourant(str.length)
      work.selection = new Selection
    }
  }
}