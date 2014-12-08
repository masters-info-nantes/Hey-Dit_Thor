package org.alma.gl

class Replace (w: Workspace) extends Command(w) {
  
   override def execute () : Unit={
     var str : String =""
     /* recuperation du debut de la chaine */
     for(x <- 0 to (w.selection.getCurseur.getPosDeb -1)){
         str = str + work.buffer.getZone.charAt(x)
     }
     /*insertion du contenu du clipboard */
     str = str + work.clipBoard.getContenu
     
     var temp :Int = w.selection.getCurseur.getPosFin 
     for(x <- temp to (work.buffer.getZone.length-1 )){
         str = str + work.buffer.getZone.charAt(x)
     }
     work.buffer.setZone(str)
   }
}