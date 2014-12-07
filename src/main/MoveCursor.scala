class MoveCursor (w : Workspace, i : Int) extends Command(w){

  override def execute() : Unit={
    work.buffer.deplaceCurseurCourant(i)
  }
}