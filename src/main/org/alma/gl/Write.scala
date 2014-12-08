package org.alma.gl

class Write (w : Workspace,var s:String) extends Command(w){
  /**
   * Commande pour ecrire dans le buffer
   */
  override def execute() : Unit={
    work.buffer.ecrire(s)
  }
}