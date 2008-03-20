package org.jetbrains.plugins.scala.lang.psi.impl.toplevel.typedef

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode

import org.jetbrains.plugins.scala.lang.parser.ScalaElementTypes
import org.jetbrains.plugins.scala.lang.lexer._
import org.jetbrains.plugins.scala.lang.psi.ScalaPsiElementImpl
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.annotations._
import org.jetbrains.plugins.scala.lang.parser.ScalaElementTypes
import org.jetbrains.plugins.scala.lang.formatting.patterns.indent._
import org.jetbrains.plugins.scala.icons.Icons

import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef._
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.templates._

/** 
* @autor Alexander.Podkhalyuzin
*/

class ScClassImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with ScClass{

  def setName(s: String) = this

  def getExtendsBlock = getChild(ScalaElementTypes.EXTENDS_BLOCK).asInstanceOf[ScExtendsBlock]

  def getTemplateParents = null

  def getMainParentName = null
  override def toString: String = "ScClass"

  override def getIcon(flags: Int) = Icons.CLASS
}