package org.alma.gl


/**
 * Reexecute une commande qui a ete undo
 * /!\ a faire uniquement apres des undo
 * sinon sa peut reexecuter une vieille commande
 */
class Redo (w : Workspace) extends Command(w) {
  
    override def execute() : Unit={
      var mem : Memento = w.gardien.redoLastCmd()
      if(mem != null){
        w.restaurerDepuisMemento(mem)
      }   
  }
}