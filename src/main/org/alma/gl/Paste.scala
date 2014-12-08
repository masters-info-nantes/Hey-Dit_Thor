package org.alma.gl

/**
 * Colle le contenu du clipboard au niveau du
 * curseur courant dans le texte
 */
class Paste (w : Workspace) extends Command(w){
   
   override def execute() : Unit={
    work.buffer.ecrire(work.clipBoard.getContenu)
  }
}