
/**
 * @author 
 */
class Memento(b: Buffer ,c : Command) {
  
  private var cmd : Command = c
  private var bufferApres :  Buffer = b.clone()
  
  def getBufferApres( ) : Buffer={
    return bufferApres
  }
  
  def setBufferApres( buf : Buffer) : Unit={
    bufferApres = buf.clone()
  }
  
  def getCmd() : Command={
    return cmd
  }
  
  def setCmd(commande : Command) : Unit={
    cmd = commande
  }
  
  override def clone() : Memento={
    var mem : Memento = new Memento(bufferApres.clone(),cmd)
    mem
  }

}