package com.olegych

import java.util.Locale

/**
  */

trait Translation {self: {def locale: Option[Locale]} =>
  implicit def translateSymbol(s: Symbol) = s"${s.name} locale:${self.locale}"

  implicit def translateTuple[T](s: (Symbol, T))
                                (implicit toParams: T => TranslationParams) =
    translateSymbol(s._1).format(toParams(s._2).p: _*)

  implicit def translationParams(p: Any) = TranslationParams(Seq(p))

  implicit def translationParams(p: Product) = TranslationParams(p.productIterator.toSeq)

  case class TranslationParams(p: Seq[Any])

}
