package org.jetbrains.plugins.scala
package lang
package scaladoc
package psi
package impl

import com.intellij.lang.ASTNode
import com.intellij.psi._
import com.intellij.psi.impl.PsiClassImplUtil
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiUtil
import org.jetbrains.plugins.scala.annotator.intention.ScalaImportTypeFix.TypeToImport
import org.jetbrains.plugins.scala.lang.psi.api.base.ScStableCodeReferenceElement
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScClass
import org.jetbrains.plugins.scala.lang.psi.impl.base.ScStableCodeReferenceElementImpl
import org.jetbrains.plugins.scala.lang.psi.impl.{ScPackageImpl, ScalaPsiElementFactory}
import org.jetbrains.plugins.scala.lang.resolve.StdKinds._
import org.jetbrains.plugins.scala.lang.resolve.processor.BaseProcessor
import org.jetbrains.plugins.scala.lang.scaladoc.psi.api.ScDocResolvableCodeReference
import org.jetbrains.plugins.scala.project._

/**
 * User: Dmitry Naydanov
 * Date: 11/30/11
 */

class ScDocResolvableCodeReferenceImpl(node: ASTNode) extends ScStableCodeReferenceElementImpl(node) with ScDocResolvableCodeReference {
  private def is2_10plus: Boolean = this.scalaLanguageLevel.map(_ >= ScalaLanguageLevel.Scala_2_10).getOrElse(true)

  override def getKinds(incomplete: Boolean, completion: Boolean) = stableImportSelector

  override def createReplacingElementWithClassName(useFullQualifiedName: Boolean, clazz: TypeToImport) = 
    if (is2_10plus) super.createReplacingElementWithClassName(true, clazz) 
    else ScalaPsiElementFactory.createDocLinkValue(clazz.qualifiedName, clazz.element.getManager)

  override protected def processQualifier(ref: ScStableCodeReferenceElement, processor: BaseProcessor) {
    if (is2_10plus) super.processQualifier(ref, processor) else pathQualifier match {
      case None =>
        val defaultPackage = ScPackageImpl(JavaPsiFacade.getInstance(getProject).findPackage(""))
        defaultPackage.processDeclarations(processor, ResolveState.initial(), null, ref)
      case Some(q: ScDocResolvableCodeReference) =>
        q.multiResolve(true).foreach(processQualifierResolveResult(_, processor, ref))
      case _ =>
    }
  }

  override def processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement, place: PsiElement): Boolean = {
    super.processDeclarations(processor, state, lastParent, place) && {
      qualifier match {
        case Some(ref: PsiReference) =>
          val el = ref.resolve()
          el match {
            case clazz: ScClass =>
              PsiClassImplUtil.processDeclarationsInClass(clazz, processor, state, null, lastParent, place, PsiUtil.getLanguageLevel(place), false)
            case _ => true
          }
        case _ => true
      }
    }
  }
}