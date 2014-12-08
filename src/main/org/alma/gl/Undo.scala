package org.alma.gl

/**
 * Commande permettant d'annuler une commande
 * retabli l'etat precedent du buffer et de la commande
 */
class Undo (w : Workspace) extends Command(w) {
  
  override def execute() : Unit={
    var mem : Memento = work.gardien.goToPreviousMemento()
    if(mem != null){
      work.restaurerDepuisMemento(mem)
    } 
  }
  
}