import org.alma.gl._
import org.junit.{Assert, Test, Before}

/**
 * Tests
 */
class Tests {
  
  var workspace : Workspace = new Workspace()
  
  
  /*initialisation */
  
  @Before
  def setUp(): Unit = {
    workspace = new Workspace()
    workspace.executeCommande(new Write(workspace,"Coucou test!"))
  }
  
  /* test de la commande write */
  
  @Test
  def testWrite() : Unit = {
    workspace.executeCommande(new Write(workspace,"Recoucou test!"))
    workspace.executeCommande(new Write(workspace,"Non"))
    Assert.assertEquals("test write", "Coucou test!Recoucou test!Non", workspace.buffer.getZone)
  }
  
  /* tests de la commande selectionner */ 
  
  @Test
  def testSelectionner() : Unit = {
    
    workspace.executeCommande(new Selectionner(workspace,0,6))
    Assert.assertEquals("Selection test contenu selection", "Coucou", workspace.selection.getContenu())
    Assert.assertEquals("Selection test contenu buffer after", "Coucou test!", workspace.buffer.getZone)
    
    workspace.executeCommande(new Selectionner(workspace,7,11))
    Assert.assertEquals("Selection test contenu selection 2", "test", workspace.selection.getContenu())
    Assert.assertEquals("Selection test contenu buffer after 2 ", "Coucou test!", workspace.buffer.getZone)
  }
  
  @Test 
  def testDeplaceSelection() : Unit={
    var selec : Selectionner =new Selectionner(workspace,0,6)
    workspace.executeCommande(selec)
    Assert.assertEquals("Selection deplace test contenu selection", "Coucou", workspace.selection.getContenu())
    Assert.assertEquals("Selection deplace contenu buffer after selec", "Coucou test!", workspace.buffer.getZone)
    selec.deplaceCurseurDeb(4)
    Assert.assertEquals("Selection deplace test contenu selection after", "ou", workspace.selection.getContenu())
    Assert.assertEquals("Selection deplace test contenu buffer after", "Coucou test!", workspace.buffer.getZone)
    selec.deplaceCurseurFin(7)
    Assert.assertEquals("Selection deplace test contenu selection after 2", "ou ",workspace.selection.getContenu())
    Assert.assertEquals("Selection deplace test contenu buffer after 2", "Coucou test!", workspace.buffer.getZone)
    
  }
  
  /* tests de la commande copy */ 
  
  @Test
  def testCopy() : Unit = {
    workspace.executeCommande(new Selectionner(workspace,0,6))
    workspace.executeCommande(new Copy(workspace))
    Assert.assertEquals("Copy test contenu selection", "Coucou", workspace.selection.getContenu())
    Assert.assertEquals("Copy test contenu buffer first", "Coucou test!", workspace.buffer.getZone)
    Assert.assertEquals("Copy contenu clipboard first ", "Coucou", workspace.clipBoard.getContenu)
    
    workspace.executeCommande(new Selectionner(workspace,7,11))
    workspace.executeCommande(new Copy(workspace))
    Assert.assertEquals("Copy test contenu selection", "test", workspace.selection.getContenu())
    Assert.assertEquals("Copy test contenu buffer second", "Coucou test!", workspace.buffer.getZone)
    Assert.assertEquals("Copy contenu clipboard second", "test", workspace.clipBoard.getContenu)
    
  }

  /* tests de la commande paste */
  
  @Test
  def testPaste() : Unit={
    workspace.executeCommande(new Selectionner(workspace,0,6))
    workspace.executeCommande(new Copy(workspace))
    Assert.assertEquals("paste test contenu selection", "Coucou", workspace.selection.getContenu())
    Assert.assertEquals("paste test contenu buffer first", "Coucou test!", workspace.buffer.getZone)
    Assert.assertEquals("paste contenu clipboard first ", "Coucou", workspace.clipBoard.getContenu)
    
    workspace.executeCommande(new Paste(workspace))
    Assert.assertEquals("paste Unique test contenu selection after", "Coucou", workspace.selection.getContenu())
    Assert.assertEquals("paste Unique contenu clipboard after ", "Coucou", workspace.clipBoard.getContenu)
    Assert.assertEquals("paste Unique test contenu buffer after", "Coucou test!Coucou", workspace.buffer.getZone)
    
    workspace.executeCommande(new Paste(workspace))
    Assert.assertEquals("paste Unique test contenu selection after 2", "Coucou", workspace.selection.getContenu())
    Assert.assertEquals("paste Unique contenu clipboard after 2", "Coucou", workspace.clipBoard.getContenu)
    Assert.assertEquals("paste Unique test contenu buffer after 2 ", "Coucou test!CoucouCoucou", workspace.buffer.getZone)
  }
  
  /*tests de la commande cut*/

  
  @Test
  def testCut() : Unit={
     Assert.assertEquals("cut test contenu buffer ", "Coucou test!", workspace.buffer.getZone)
     
     workspace.executeCommande(new Selectionner(workspace,0,6))
     Assert.assertEquals("cut test contenu selection", "Coucou", workspace.selection.getContenu())
     
     workspace.executeCommande(new Cut(workspace))
     Assert.assertEquals("cut contenu clipboard first ", "Coucou", workspace.clipBoard.getContenu)
     Assert.assertEquals("cut test buffer first", " test!", workspace.buffer.getZone)   
     
     workspace.executeCommande(new Selectionner(workspace,1,6))
     Assert.assertEquals("cut test contenu selection 2", "test!", workspace.selection.getContenu())
     
     workspace.executeCommande(new Cut(workspace))
     Assert.assertEquals("cut contenu clipboard second", "test!",workspace.clipBoard.getContenu)
     Assert.assertEquals("cut test buffer second", " ", workspace.buffer.getZone)   
  }
  
  /* tests de la commande delete */
  @Test
  def testDelete() : Unit={
     Assert.assertEquals("delete test contenu buffer first", "Coucou test!", workspace.buffer.getZone)
     var selec : Selectionner = new Selectionner(workspace,0,6)
     workspace.executeCommande(selec)
     Assert.assertEquals("deletetest contenu selection", "Coucou",workspace.selection.getContenu())
     workspace.executeCommande(new Delete(workspace))
     Assert.assertEquals("delete test buffer After 1", " test!", workspace.buffer.getZone) 
     workspace.executeCommande(new Delete(workspace))
     Assert.assertEquals("delete test buffer After 2", " test!", workspace.buffer.getZone)
     selec = new Selectionner(workspace,0,5)
     workspace.executeCommande(selec)
     workspace.executeCommande(new Delete(workspace))
     Assert.assertEquals("delete  test buffer After 3", "!", workspace.buffer.getZone) 
  }
  
  /* tests de la commande replace */
  
  @Test 
  def testReplace() :Unit={
    Assert.assertEquals("Replace Unique test contenu buffer ", "Coucou test!", workspace.buffer.getZone)
    
    var selec : Selectionner = new Selectionner(workspace,0,6)
    workspace.executeCommande(selec)
    workspace.executeCommande(new Copy(workspace))
    Assert.assertEquals("Replace contenu clipboard  ", "Coucou", workspace.clipBoard.getContenu)
    Assert.assertEquals("Replace Unique test contenu buffer ", "Coucou test!", workspace.buffer.getZone)
    
    //selec = new Selection(workspace,7,10)
    selec.deplaceCurseurDeb(7)
    selec.deplaceCurseurFin(11)
    Assert.assertEquals("Replace contenu clipboard  ", "Coucou", workspace.clipBoard.getContenu)
    workspace.executeCommande(new Replace(workspace))
    Assert.assertEquals("Replace contenu clipboard  1", "Coucou", workspace.clipBoard.getContenu)
    Assert.assertEquals("Replace contenu clipboard  res 1", "Coucou Coucou!", workspace.buffer.getZone)
    
    selec.deplaceCurseurDeb(6)
    selec.deplaceCurseurFin(7)
    workspace.executeCommande(new Replace(workspace))
    Assert.assertEquals("Replace contenu clipboard  2", "Coucou", workspace.clipBoard.getContenu)
    Assert.assertEquals("Replace contenu clipboard  res 2", "CoucouCoucouCoucou!", workspace.buffer.getZone)
  }
  
  
  /* tests de la commande Undo */
  
  @Test 
  def testUndo() : Unit={
    Assert.assertEquals("Undo  test contenu buffer debut", "Coucou test!", workspace.buffer.getZone)
  /*Assert.assertEquals("Undo Multiple test gardien 0 deb ap","" , workspace.gardien.listMemento(0).getBufferApres.getZone)
    Assert.assertEquals("Undo Multiple test gardien write ap","Coucou test!" , workspace.gardien.listMemento(1).getBufferApres.getZone)
  */  
   workspace.executeCommande(new Write(workspace,"Recoucou test!"))
    Assert.assertEquals("Undo  test contenu buffer after Write", "Coucou test!Recoucou test!", workspace.buffer.getZone)
 /* Assert.assertEquals("Undo Multiple test gardien 0 deb ap","" , workspace.gardien.listMemento(0).getBufferApres.getZone)
    Assert.assertEquals("Undo Multiple test gardien write ap","Coucou test!" , workspace.gardien.listMemento(1).getBufferApres.getZone)
    Assert.assertEquals("Undo Multiple test gardien write 2 ap","Coucou test!Recoucou test!" , workspace.gardien.listMemento(2).getBufferApres.getZone)
  */  
  
    workspace.executeCommande(new Undo(workspace))
    Assert.assertEquals("Undo e test contenu buffer 1", "Coucou test!", workspace.buffer.getZone)
 /* Assert.assertEquals("Undo Multiple test deb ap","" , workspace.gardien.listMemento(0).getBufferApres.getZone)
    Assert.assertEquals("Undo Multiple test gardien write ap","Coucou test!" , workspace.gardien.listMemento(1).getBufferApres.getZone)
 */   
    
    workspace.executeCommande(new Undo(workspace))
   Assert.assertEquals("Undo  test contenu buffer 2", "", workspace.buffer.getZone)
  /* Assert.assertEquals("Undo Multiple test deb ap","" , workspace.gardien.listMemento(0).getBufferApres.getZone)
  */
  }
  
  @Test 
  def testOverUndo() : Unit ={
    Assert.assertEquals("Undo over test contenu buffer debut", "Coucou test!", workspace.buffer.getZone)
  
    workspace.executeCommande(new Write(workspace,"Recoucou test!"))
    Assert.assertEquals("Undo over test contenu buffer after Write", "Coucou test!Recoucou test!", workspace.buffer.getZone) 
  
    workspace.executeCommande(new Undo(workspace))
    Assert.assertEquals("Undo over test contenu buffer 1", "Coucou test!", workspace.buffer.getZone)
  
    
    workspace.executeCommande(new Undo(workspace))
    Assert.assertEquals("Undo over test contenu buffer 2", "", workspace.buffer.getZone)
    
    workspace.executeCommande(new Undo(workspace))
    Assert.assertEquals("Undo over test contenu buffer 3", "", workspace.buffer.getZone)
    workspace.executeCommande(new Undo(workspace))
    Assert.assertEquals("Undo over test contenu buffer 4", "", workspace.buffer.getZone)

  }
  
  /*test de la commande redo */
  // cette fonction est comme unique car on ne peut redo qu'une seule commande
  @Test
  def testRedo() : Unit={
    Assert.assertEquals("redo test contenu buffer deb", "Coucou test!", workspace.buffer.getZone)
    
    workspace.executeCommande(new Undo(workspace))
    Assert.assertEquals("redo test contenu buffer undo", "", workspace.buffer.getZone)
    
    workspace.executeCommande(new Redo(workspace))
    Assert.assertEquals("redo test contenu buffer redo", "Coucou test!", workspace.buffer.getZone) 
    workspace.executeCommande(new Redo(workspace))
    Assert.assertEquals("Undo Unique test contenu buffer reredo", "Coucou test!", workspace.buffer.getZone) 
  }
  
  /* test de la commande MoveCursor */
  
  @Test
  def testMoveCursor() : Unit = {
    Assert.assertEquals("moveCursor  test contenu buffer ", "Coucou test!", workspace.buffer.getZone)
    
    workspace.executeCommande(new MoveCursor(workspace,6))
    Assert.assertEquals("moveCursor test pos curseur ", 6, workspace.buffer.getCurseurCourant.getPos)
    workspace.executeCommande(new Write(workspace,"testception"))
    Assert.assertEquals("moveCursor test write 1 ","Coucoutestception test!" , workspace.buffer.getZone)
    
    workspace.executeCommande(new MoveCursor(workspace,10))
    Assert.assertEquals("moveCursor Unique test pos curseur ", 10, workspace.buffer.getCurseurCourant.getPos)
    workspace.executeCommande(new Write(workspace,"testception"))
    Assert.assertEquals("moveCursor test write 2 ","Coucoutesttestceptionception test!" , workspace.buffer.getZone)
    
  }
    
  /* tests de la commande macro*/
  
  @Test
  def testMacro() :Unit = {
    Assert.assertEquals("macrotest contenu buffer ", "Coucou test!", workspace.buffer.getZone)
    
    var mac = new Macro(workspace)
    mac.addCommand(new Write(workspace,"Recoucou test!"))
    mac.addCommand(new MoveCursor(workspace,6))
    mac.addCommand(new Write(workspace,"testception"))
    workspace.executeCommande(mac)
    Assert.assertEquals("macro test contenu buffer ", "Coucoutestception test!Recoucou test!", workspace.buffer.getZone)
  }
  
  /* tests reexecution commande */
  
  
  @Test
  def testReexecute() :Unit={
    Assert.assertEquals("reexe test contenu buffer ", "Coucou test!", workspace.buffer.getZone)
    workspace.reexecuteCommande()
    Assert.assertEquals("reexe test contenu buffer 1", "Coucou test!Coucou test!", workspace.buffer.getZone)
    workspace.reexecuteCommande()
    Assert.assertEquals("reexe test contenu buffer 2", "Coucou test!Coucou test!Coucou test!", workspace.buffer.getZone)
  }
}