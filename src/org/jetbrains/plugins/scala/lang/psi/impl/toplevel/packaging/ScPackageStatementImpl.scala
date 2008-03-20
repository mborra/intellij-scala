package org.jetbrains.plugins.scala.lang.psi.impl.toplevel.packaging

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

import org.jetbrains.plugins.scala.lang.psi.api.toplevel.packaging._

/** 
* Created by IntelliJ IDEA.
* User: Alexander.Podkhalyuz
* Date: 20.02.2008
* Time: 18:35:16
* To change this template use File | Settings | File Templates.
*/

class ScPackageStatementImpl(node: ASTNode) extends ScalaPsiElementImpl(node) with ScPackageStatement{
  override def toString = "ScPackageStatement"

 /* @Nullable
  def getFullPackageName: String = {
    val qualId = getChild(ScalaElementTypes.QUAL_ID).asInstanceOf[ScQualId]
    if (qualId == null) null else qualId.getFullName
  }*/
}