package com.sksamuel.hoplite.decoder

import arrow.core.invalid
import arrow.core.valid
import com.sksamuel.hoplite.ConfigFailure
import com.sksamuel.hoplite.ConfigResult
import com.sksamuel.hoplite.DecoderContext
import com.sksamuel.hoplite.DoubleNode
import com.sksamuel.hoplite.LongNode
import com.sksamuel.hoplite.Masked
import com.sksamuel.hoplite.StringNode
import com.sksamuel.hoplite.Node
import kotlin.reflect.KType

class MaskedDecoder : NullHandlingDecoder<Masked> {
  override fun supports(type: KType): Boolean = type.classifier == Masked::class
  override fun safeDecode(node: Node,
                          type: KType,
                          context: DecoderContext): ConfigResult<Masked> = when (node) {
    is StringNode -> Masked(node.value).valid()
    is LongNode -> Masked(node.value.toString()).valid()
    is DoubleNode -> Masked(node.value.toString()).valid()
    else -> ConfigFailure.DecodeError(node, type).invalid()
  }
}
