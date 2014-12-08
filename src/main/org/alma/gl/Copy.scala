package org.alma.gl



/**
 * @author E120404Z
 */
class Copy (w : Workspace) extends Command(w){

  override def execute() : Unit={
    work.clipBoard.setContenu(work.selection.getContenu())
  }
}