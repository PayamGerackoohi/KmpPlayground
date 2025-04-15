package com.payam1991gr.kmp.playground.data.model

import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

enum class IoItem(val labelRes: StringResource) {
    Datastore(Res.string.io_datastore),
//    Database(Res.string.io_database),
    API(Res.string.io_api),
//    File(Res.string.io_file),
}

/*
- [ ] Datastore
  - [ ] Key-Value
  - [ ] Protobuf
- [ ] Database
  - [ ] Room
  - [ ] SqlDelight
- [ ] API
  - [ ] REST
  - [ ] GraphQL
  - [ ] GRPC
- [ ] File
*/
