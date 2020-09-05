package com.anniekobia.harrypotter.utils

import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.remote.model.CharacterUIModel

fun Character.toResponse() = CharacterUIModel(this.actor)
fun Character.toEntity() = CharacterUIModel(this.actor)