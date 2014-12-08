package org.alma.gl



/**
 * @author E120404Z
 */
class Paste (w : Workspace) extends Command(w){
   
   override def execute() : Unit={
    work.buffer.ecrire(work.clipBoard.getContenu)
  }
}