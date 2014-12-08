package org.alma.gl



/**
 * Commande permettant de copier la selection dans le presse papier
 */
class Copy (w : Workspace) extends Command(w){

  override def execute() : Unit={
    work.clipBoard.setContenu(work.selection.getContenu())
  }
}