package screenshots.util.order

object Order : Node() {
    val Home = Leaf("Home", this)

    object Components : Node("Components", this) {
        val List = Leaf("List", this)

        object Carousel : Node("Carousel", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object Dialog : Node("Dialog", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object Picker : Node("Picker", this) {
            object DateTime : Node("DateTime", this) {
                val Preview = Leaf("Preview", this)
                val Code = Leaf("Code", this)
            }
        }

        object PullToRefresh : Node("PullToRefresh", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }
    }

    object Animations : Node("Animations", this) {
        val List = Leaf("List", this)

        object AnimateXAs : Node("AnimateXAs", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object AnimatedContent : Node("AnimatedContent", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object AnimatedVisibility : Node("AnimatedVisibility", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object Crossfade : Node("Crossfade", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }
    }

    object Graphics : Node("Graphics", this) {
        val List = Leaf("List", this)

//        object Charts : Node("Charts", this) {
//            val Preview = Leaf("Preview", this)
//            val Code = Leaf("Code", this)
//        }

        object ColorScheme : Node("ColorScheme", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object Icons : Node("Icons", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

//        object OpenGl : Node("OpenGl", this) {
//            val Preview = Leaf("Preview", this)
//            val Code = Leaf("Code", this)
//        }
    }

    object Io : Node("Io", this) {
        val List = Leaf("List", this)

        object Api : Node("Api", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

//        object Database : Node("Database", this) {
//            val Preview = Leaf("Preview", this)
//            val Code = Leaf("Code", this)
//        }

        object Datastore : Node("Datastore", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

//        object File : Node("File", this) {
//            val Preview = Leaf("Preview", this)
//            val Code = Leaf("Code", this)
//        }
    }

    object Miscellaneous : Node("Miscellaneous", this) {
        val List = Leaf("List", this)

//        object Ble : Node("Ble", this) {
//            val Preview = Leaf("Preview", this)
//            val Code = Leaf("Code", this)
//        }

        object Cpp : Node("C++", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

        object DateTime : Node("DateTime", this) {
            val Preview = Leaf("Preview", this)
            val Code = Leaf("Code", this)
        }

//        object Pdf : Node("Pdf", this) {
//            val Preview = Leaf("Preview", this)
//            val Code = Leaf("Code", this)
//        }
    }

    object Modules : Node("Modules", this) {
        val RandomImage = Leaf("RandomImage", this)
    }
}
