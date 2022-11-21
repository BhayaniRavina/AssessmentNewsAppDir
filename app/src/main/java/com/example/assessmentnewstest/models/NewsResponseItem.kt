package com.example.assessmentnewstest.models

import com.google.gson.annotations.SerializedName
import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "news")
data class NewsResponseItem(
    @PrimaryKey(autoGenerate = true)
    var index: Int? = null,
    val id: Long,
    val title: String,
    val description: String,
    val source: String,
    val sourceId: String,
    val version: String,
    val publishedAt: Long,
    val readablePublishedAt: String,
    val updatedAt: Long,
    val readableUpdatedAt: String,
    val type: String,
    val active: Boolean,
    val draft: Boolean,
    val embedTypes: String?,
    val typeAttributes: TypeAttributes,
    @SerializedName("images") val images: Images5,
    val language: String,
)

data class TypeAttributes(
    val uppercaseHeadline: Boolean?,
    val components: Components?,
    val url: String,
    val urlSlug: String,
    val deck: String,
    val imageSmall: String,
    val imageLarge: String,
    val imageAspects: String,
    val flag: String,
    val flags: Flags5,
    val displayComments: Boolean,
    val commentsSectionId: String,
    @SerializedName("trending") val trending: Trending5,
    val mediaDuration: Any?,
    val media: Any?,
    val mediaId: Any?,
    val mediaStreamType: Any?,
    val mediaCaptionUrl: Any?,
    val show: String,
    val showSlug: String,
    @SerializedName("body") val body: Body5,
    val sectionList: List<String>,
    val sectionLabels: List<String>,
    val categories: List<Category>,
    val contextualHeadlines: List<ContextualHeadline>,
    @SerializedName("author") val author: Author9,
    @SerializedName("headline") val headline: Headline4?,
)

data class Components(
    val mainContent: MainContent,
    val mainVisual: MainVisual,
    val primary: List<Primary>,
    val secondary: List<Secondary>,
    val tertiary: List<Any?>,
)

data class MainContent(
    val id: Long,
    val title: String,
    val description: String,
    val source: String,
    val sourceId: String,
    val version: String,
    val publishedAt: Long,
    val readablePublishedAt: String,
    val updatedAt: Long,
    val readableUpdatedAt: String,
    val type: String,
    val active: Boolean,
    val draft: Boolean,
    val embedTypes: String,
    @SerializedName("typeAttributes") val typeAttributes: TypeAttributes2,
    val images: Images,
    val language: String,
)

data class TypeAttributes2(
    val url: String,
    val urlSlug: String,
    val deck: String,
    val imageSmall: String,
    val imageLarge: String,
    val imageAspects: String,
    val flag: String,
    val flags: Flags,
    val displayComments: Boolean,
    val commentsSectionId: String,
    val trending: Trending,
    val mediaDuration: Any?,
    val media: Any?,
    val mediaId: Any?,
    val mediaStreamType: Any?,
    val mediaCaptionUrl: Any?,
    val show: String,
    val showSlug: String,
    val body: Body,
    val sectionList: List<String>,
    val sectionLabels: List<String>,
    val categories: Any?,
    val contextualHeadlines: Any?,
    val headline: Headline,
    val author: Author,
)

data class Flags(
    val status: String,
    val label: String,
)

data class Trending(
    val numViewers: Long,
    @SerializedName("numViewersSRS")
    val numViewersSrs: Long,
)

data class Body(
    val containsAudio: Boolean,
    val containsVideo: Boolean,
    val containsPhotogallery: Boolean,
)

data class Headline(
    val type: String,
    val mediaId: String,
)

data class Author(
    val name: String,
    val display: String,
    val image: String,
)

data class Images(
    @SerializedName("square_140")
    val square140: String,
)

data class MainVisual(
    val id: Long,
    val sourceId: String,
    val type: String,
    val url: String?,
    val width: Long?,
    val height: Long?,
    val title: String,
    val description: String,
    val credit: String?,
    val altText: String?,
    val createdAt: Long?,
    val modifiedAt: Long?,
    val readableCreatedAt: String?,
    val readableModifiedAt: String?,
    val useOriginalImage: Boolean?,
    val derivatives: Derivatives?,
    val version: String,
    val source: String?,
    val publishedAt: Long?,
    val readablePublishedAt: String?,
    val updatedAt: Long?,
    val readableUpdatedAt: String?,
    val active: Boolean?,
    val draft: Boolean?,
    val embedTypes: Any?,
    @SerializedName("typeAttributes") val typeAttributes: TypeAttributes3?,
    @SerializedName("images") val images: Images2?,
    val language: String?,
)

data class Derivatives(
    @SerializedName("6x9_140")
    val n6x9140: n6x9140,
    @SerializedName("6x9_780")
    val n6x9780: n6x9780,
    @SerializedName("6x9_460")
    val n6x9460: n6x9460,
    @SerializedName("6x9_380")
    val n6x9380: n6x9380,
    @SerializedName("6x9_940")
    val n6x9940: n6x9940,
    @SerializedName("6x9_620")
    val n6x9620: n6x9620,
    @SerializedName("16x9_1180")
    val n16x91180: n16x91180,
    @SerializedName("square_1180")
    val square1180: Square1180,
    @SerializedName("original_140")
    val original140: Original140,
    @SerializedName("6x9_220")
    val n6x9220: n6x9220,
    @SerializedName("6x9_300")
    val n6x9300: n6x9300,
    @SerializedName("original_380")
    val original380: Original380,
    @SerializedName("4x3_140")
    val n4x3140: n4x3140,
    @SerializedName("4x3_460")
    val n4x3460: n4x3460,
    @SerializedName("4x3_780")
    val n4x3780: n4x3780,
    @SerializedName("16x9_60")
    val n16x960: n16x960,
    @SerializedName("26x11_60")
    val n26x1160: n26x1160,
    @SerializedName("4x3_380")
    val n4x3380: n4x3380,
    @SerializedName("4x3_620")
    val n4x3620: n4x3620,
    @SerializedName("4x3_940")
    val n4x3940: n4x3940,
    @SerializedName("4x3_220")
    val n4x3220: n4x3220,
    @SerializedName("4x3_300")
    val n4x3300: n4x3300,
    @SerializedName("16x9tight_60")
    val n16x9tight60: n16x9tight60,
    @SerializedName("original_1180")
    val original1180: Original1180,
    @SerializedName("16x9tight_1180")
    val n16x9tight1180: n16x9tight1180,
    @SerializedName("26x11_780")
    val n26x11780: n26x11780,
    @SerializedName("26x11_220")
    val n26x11220: n26x11220,
    @SerializedName("26x11_380")
    val n26x11380: n26x11380,
    @SerializedName("16x9tight_380")
    val n16x9tight380: n16x9tight380,
    @SerializedName("26x11_140")
    val n26x11140: n26x11140,
    @SerializedName("16x9tight_460")
    val n16x9tight460: n16x9tight460,
    @SerializedName("26x11_460")
    val n26x11460: n26x11460,
    @SerializedName("16x9tight_140")
    val n16x9tight140: n16x9tight140,
    @SerializedName("16x9tight_220")
    val n16x9tight220: n16x9tight220,
    @SerializedName("3x4_620")
    val n3x4620: n3x4620,
    @SerializedName("16x9tight_780")
    val n16x9tight780: n16x9tight780,
    @SerializedName("3x4_940")
    val n3x4940: n3x4940,
    @SerializedName("square_60")
    val square60: Square60,
    @SerializedName("26x11_300")
    val n26x11300: n26x11300,
    @SerializedName("16x9tight_620")
    val n16x9tight620: n16x9tight620,
    @SerializedName("3x4_220")
    val n3x4220: n3x4220,
    @SerializedName("26x11_620")
    val n26x11620: n26x11620,
    @SerializedName("16x9tight_300")
    val n16x9tight300: n16x9tight300,
    @SerializedName("square_300")
    val square300: Square300,
    @SerializedName("26x11_940")
    val n26x11940: n26x11940,
    @SerializedName("square_620")
    val square620: Square620,
    @SerializedName("16x9tight_940")
    val n16x9tight940: n16x9tight940,
    @SerializedName("3x4_300")
    val n3x4300: n3x4300,
    @SerializedName("square_940")
    val square940: Square940,
    @SerializedName("square_460")
    val square460: Square460,
    @SerializedName("square_780")
    val square780: Square780,
    @SerializedName("square_220")
    val square220: Square220,
    @SerializedName("square_380")
    val square380: Square380,
    @SerializedName("square_140")
    val square140: Square140,
    @SerializedName("original_60")
    val original60: Original60,
    @SerializedName("6x9_60")
    val n6x960: n6x960,
    @SerializedName("6x9_1180")
    val n6x91180: n6x91180,
    @SerializedName("16x9_940")
    val n16x9940: n16x9940,
    @SerializedName("16x9_300")
    val n16x9300: n16x9300,
    @SerializedName("16x9_620")
    val n16x9620: n16x9620,
    @SerializedName("4x3_60")
    val n4x360: n4x360,
    @SerializedName("16x9_780")
    val n16x9780: n16x9780,
    @SerializedName("16x9_220")
    val n16x9220: n16x9220,
    @SerializedName("4x3_1180")
    val n4x31180: n4x31180,
    @SerializedName("16x9_140")
    val n16x9140: n16x9140,
    @SerializedName("16x9_460")
    val n16x9460: n16x9460,
    @SerializedName("16x9_380")
    val n16x9380: n16x9380,
    @SerializedName("3x4_60")
    val n3x460: n3x460,
    @SerializedName("3x4_140")
    val n3x4140: n3x4140,
    @SerializedName("original_220")
    val original220: Original220,
    @SerializedName("3x4_460")
    val n3x4460: n3x4460,
    @SerializedName("original_780")
    val original780: Original780,
    @SerializedName("3x4_780")
    val n3x4780: n3x4780,
    @SerializedName("original_460")
    val original460: Original460,
    @SerializedName("original_940")
    val original940: Original940,
    @SerializedName("original_620")
    val original620: Original620,
    @SerializedName("26x11_1180")
    val n26x111180: n26x111180,
    @SerializedName("original_300")
    val original300: Original300,
    @SerializedName("3x4_380")
    val n3x4380: n3x4380,
    @SerializedName("3x4_1180")
    val n3x41180: n3x41180,
)

data class n6x9140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x91180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square1180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x9300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x960(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x1160(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x3300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight60(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original1180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight1180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square60(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x11940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original60(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x960(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n6x91180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x360(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n4x31180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4140(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original220(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4780(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original460(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original940(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original620(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n26x111180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original300(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x4380(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n3x41180(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class TypeAttributes3(
    val url: String,
    val urlSlug: String,
    val deck: String,
    val imageSmall: String,
    val imageLarge: String,
    val imageAspects: String,
    val flag: String,
    @SerializedName("flags") val flags: Flags2,
    val displayComments: Boolean,
    val commentsSectionId: String,
    @SerializedName("trending") val trending: Trending2,
    val mediaDuration: Long,
    val media: Any?,
    val mediaId: String,
    val mediaStreamType: String,
    val mediaCaptionUrl: String,
    val show: String,
    val showSlug: String,
    @SerializedName("body") val body: Body2,
    val sectionList: List<Any?>,
    val sectionLabels: List<Any?>,
    val categories: Any?,
    val contextualHeadlines: Any?,
    @SerializedName("author") val author: Author2,
)

data class Flags2(
    val status: String,
    val label: String,
)

data class Trending2(
    val numViewers: Any?,
    @SerializedName("numViewersSRS")
    val numViewersSrs: Any?,
)

data class Body2(
    val containsAudio: Boolean,
    val containsVideo: Boolean,
    val containsPhotogallery: Boolean,
)

data class Author2(
    val name: String,
    val display: String,
    val image: String,
)

data class Images2(
    @SerializedName("square_140")
    val square140: String,
)

data class Primary(
    val localheadline: String,
    val type: String,
    val content: Content,
    val localHeadline: String,
    val item: Item,
)

data class Content(
    val flag: String,
    val deck: String,
    val description: String,
    val headlinemedia: Headlinemedia,
    val language: String,
    val epoch: Epoch,
    val type: String,
    val title: String,
    val embeddedtypes: String,
    val extattrib: Extattrib,
    val id: String,
    val departments: List<Department>,
    val headline: String,
    val shareheadline: String,
    val pubdate: String,
    val headlineimage: Headlineimage,
    val categorization: Categorization,
    val jsonurl: String,
    @SerializedName("author") val author: Author3,
    val label: String,
    val url: String,
    val intlinks: List<Intlink>,
    val lastupdate: String,
    val status: String,
    val authors: List<Author4>,
)

data class Headlinemedia(
    val jsonurl: String,
    val deck: String,
    val alt: String,
    val description: String,
    val title: String,
    val type: String,
    val url: String,
    val originalimage: Originalimage,
    @SerializedName("derivatives") val derivatives: Derivatives2,
    val size: String,
    val useoriginalimage: Boolean,
    val originalimageurl: String,
    val id: String,
    val credit: String,
    val headline: String,
)

data class Originalimage(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives2(
    @SerializedName("square_220")
    val square220: Square2202,
    @SerializedName("16x9_940")
    val n16x9940: n16x99402,
    @SerializedName("16x9_300")
    val n16x9300: n16x93002,
    @SerializedName("16x9_620")
    val n16x9620: n16x96202,
    @SerializedName("original_620")
    val original620: Original6202,
    @SerializedName("original_300")
    val original300: Original3002,
    @SerializedName("16x9tight_140")
    val n16x9tight140: n16x9tight1402,
    @SerializedName("square_140")
    val square140: Square1402,
    @SerializedName("16x9_780")
    val n16x9780: n16x97802,
    @SerializedName("square_60")
    val square60: Square602,
    @SerializedName("16x9_460")
    val n16x9460: n16x94602,
)

data class Square2202(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x99402(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x93002(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x96202(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original6202(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original3002(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight1402(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square1402(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x97802(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square602(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x94602(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Epoch(
    val ontime: String,
    val offtime: String,
    val lastupdate: String,
    val pubdate: String,
)

data class Extattrib(
    val urlslug: String,
    val hasvideo: Boolean,
    val displaycomments: Boolean,
    val vfsection: String,
    val syndicate: String,
    val hasaudio: Boolean,
    val hasgallery: Boolean,
    val olympictags: Any?,
)

data class Department(
    val name: String,
    val label: String,
    val id: String,
    val referencename: String,
)

data class Headlineimage(
    val jsonurl: String,
    val deck: String,
    val alt: String,
    val description: String,
    val title: String,
    val type: String,
    val url: String,
    @SerializedName("originalimage") val originalimage: Originalimage2,
    @SerializedName("derivatives") val derivatives: Derivatives3,
    val size: String,
    val useoriginalimage: Boolean,
    val originalimageurl: String,
    val id: String,
    val credit: String,
    val headline: String,
)

data class Originalimage2(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives3(
    @SerializedName("square_220")
    val square220: Square2203,
    @SerializedName("16x9_940")
    val n16x9940: n16x99403,
    @SerializedName("16x9_300")
    val n16x9300: n16x93003,
    @SerializedName("16x9_620")
    val n16x9620: n16x96203,
    @SerializedName("original_620")
    val original620: Original6203,
    @SerializedName("original_300")
    val original300: Original3003,
    @SerializedName("16x9tight_140")
    val n16x9tight140: n16x9tight1403,
    @SerializedName("square_140")
    val square140: Square1403,
    @SerializedName("16x9_780")
    val n16x9780: n16x97803,
    @SerializedName("square_60")
    val square60: Square603,
    @SerializedName("16x9_460")
    val n16x9460: n16x94603,
)

data class Square2203(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x99403(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x93003(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x96203(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original6203(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original3003(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight1403(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square1403(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x97803(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square603(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x94603(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Categorization(
    val dimensions: List<Dimension>,
)

data class Dimension(
    val entities: List<Entity>,
    val name: String,
    val id: String,
)

data class Entity(
    val name: String,
    val id: String,
)

data class Author3(
    val bio: Bio,
    val authors: List<String>,
)

data class Bio(
    val name: String?,
    val photo: Photo,
    val id: String?,
    val biography: String?,
    val title: String?,
)

data class Photo(
    val jsonurl: String?,
    val deck: String?,
    val alt: String?,
    val description: String?,
    val title: String?,
    val type: String?,
    val url: String?,
    @SerializedName("originalimage") val originalimage: Originalimage3?,
    @SerializedName("derivatives") val derivatives: Derivatives4?,
    val size: String?,
    val useoriginalimage: Boolean?,
    val originalimageurl: String?,
    val id: String?,
    val credit: String?,
    val headline: String?,
)

data class Originalimage3(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives4(
    @SerializedName("square_140")
    val square140: Square1404,
    @SerializedName("square_300")
    val square300: Square3002,
    @SerializedName("square_620")
    val square620: Square6202,
)

data class Square1404(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square3002(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square6202(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Intlink(
    val jsonurl: String,
    val description: String,
    val id: String,
    val type: String,
    val title: String,
    val url: String,
    val embeddedtypes: String,
    val shareheadline: String,
)

data class Author4(
    val name: String,
    @SerializedName("photo") val photo: Photo2,
    val id: String,
    val biography: String,
    val type: String,
    val title: String,
    val url: String,
)

data class Photo2(
    val jsonurl: String,
    val deck: String,
    val alt: String,
    val description: String,
    val title: String,
    val type: String,
    val url: String,
    @SerializedName("originalimage") val originalimage: Originalimage4,
    @SerializedName("derivatives") val derivatives: Derivatives5,
    val size: String,
    val useoriginalimage: Boolean,
    val originalimageurl: String,
    val id: String,
    val credit: String,
    val headline: String,
)

data class Originalimage4(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives5(
    @SerializedName("square_140")
    val square140: Square1405,
    @SerializedName("square_300")
    val square300: Square3003,
    @SerializedName("square_620")
    val square620: Square6203,
)

data class Square1405(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square3003(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square6203(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Item(
    val id: Long,
    val title: String,
    val description: String,
    val source: String,
    val sourceId: String,
    val version: String,
    val publishedAt: Long,
    val readablePublishedAt: String,
    val updatedAt: Long,
    val readableUpdatedAt: String,
    val type: String,
    val active: Boolean,
    val draft: Boolean,
    val embedTypes: String,
    @SerializedName("typeAttributes") val typeAttributes: TypeAttributes4,
    @SerializedName("images") val images: Images3,
    val language: String,
)

data class TypeAttributes4(
    val url: String,
    val urlSlug: String,
    val deck: String,
    val imageSmall: String,
    val imageLarge: String,
    val imageAspects: String,
    val flag: String,
    @SerializedName("flags") val flags: Flags3,
    val displayComments: Boolean,
    val commentsSectionId: String,
    @SerializedName("trending") val trending: Trending3,
    val mediaDuration: Any?,
    val media: Any?,
    val mediaId: Any?,
    val mediaStreamType: Any?,
    val mediaCaptionUrl: Any?,
    val show: String,
    val showSlug: String,
    @SerializedName("body") val body: Body3,
    val sectionList: List<String>,
    val sectionLabels: List<String>,
    val categories: Any?,
    val contextualHeadlines: Any?,
    @SerializedName("headline") val headline: Headline2,
    @SerializedName("author") val author: Author5,
)

data class Flags3(
    val status: String,
    val label: String,
)

data class Trending3(
    val numViewers: Long,
    @SerializedName("numViewersSRS")
    val numViewersSrs: Long,
)

data class Body3(
    val containsAudio: Boolean,
    val containsVideo: Boolean,
    val containsPhotogallery: Boolean,
)

data class Headline2(
    val type: String,
    val mediaId: String,
)

data class Author5(
    val name: String,
    val display: String,
    val image: String,
)

data class Images3(
    @SerializedName("square_140")
    val square140: String,
)

data class Secondary(
    val localheadline: String,
    val type: String,
    @SerializedName("content") val content: Content2,
    val localHeadline: String,
    @SerializedName("item") val item: Item2,
)

data class Content2(
    val flag: String,
    val deck: String,
    val description: String,
    @SerializedName("headlinemedia") val headlinemedia: Headlinemedia2,
    val language: String,
    @SerializedName("epoch") val epoch: Epoch2,
    val type: String,
    val title: String,
    val embeddedtypes: String,
    @SerializedName("extattrib") val extattrib: Extattrib2,
    val id: String,
    val departments: List<Department2>,
    val headline: String,
    val shareheadline: String,
    val pubdate: String,
    @SerializedName("headlineimage") val headlineimage: Headlineimage2,
    @SerializedName("categorization") val categorization: Categorization2,
    val jsonurl: String,
    @SerializedName("author") val author: Author6,
    val label: String,
    val url: String,
    val intlinks: List<Intlink2>,
    val lastupdate: String,
    val status: String,
    val authors: List<Author7>,
)

data class Headlinemedia2(
    val jsonurl: String,
    val deck: String,
    val alt: String,
    val description: String,
    val title: String,
    val type: String,
    val url: String,
    @SerializedName("originalimage") val originalimage: Originalimage5,
    @SerializedName("derivatives") val derivatives: Derivatives6,
    val size: String,
    val useoriginalimage: Boolean,
    val originalimageurl: String,
    val id: String,
    val credit: String,
    val headline: String,
)

data class Originalimage5(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives6(
    @SerializedName("square_220")
    val square220: Square2204,
    @SerializedName("16x9_940")
    val n16x9940: n16x99404,
    @SerializedName("16x9_300")
    val n16x9300: n16x93004,
    @SerializedName("16x9_620")
    val n16x9620: n16x96204,
    @SerializedName("original_620")
    val original620: Original6204,
    @SerializedName("original_300")
    val original300: Original3004,
    @SerializedName("16x9tight_140")
    val n16x9tight140: n16x9tight1404,
    @SerializedName("square_140")
    val square140: Square1406,
    @SerializedName("16x9_780")
    val n16x9780: n16x97804,
    @SerializedName("square_60")
    val square60: Square604,
    @SerializedName("16x9_460")
    val n16x9460: n16x94604,
)

data class Square2204(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x99404(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x93004(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x96204(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original6204(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original3004(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight1404(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square1406(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x97804(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square604(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x94604(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Epoch2(
    val ontime: String,
    val offtime: String,
    val lastupdate: String,
    val pubdate: String,
)

data class Extattrib2(
    val urlslug: String,
    val hasvideo: Boolean,
    val displaycomments: Boolean,
    val vfsection: String,
    val syndicate: String,
    val hasaudio: Boolean,
    val hasgallery: Boolean,
    val olympictags: Any?,
)

data class Department2(
    val name: String,
    val label: String,
    val id: String,
    val referencename: String,
)

data class Headlineimage2(
    val jsonurl: String,
    val deck: String,
    val alt: String,
    val description: String,
    val title: String,
    val type: String,
    val url: String,
    @SerializedName("originalimage") val originalimage: Originalimage6,
    @SerializedName("derivatives") val derivatives: Derivatives7,
    val size: String,
    val useoriginalimage: Boolean,
    val originalimageurl: String,
    val id: String,
    val credit: String,
    val headline: String,
)

data class Originalimage6(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives7(
    @SerializedName("square_220")
    val square220: Square2205,
    @SerializedName("16x9_940")
    val n16x9940: n16x99405,
    @SerializedName("16x9_300")
    val n16x9300: n16x93005,
    @SerializedName("16x9_620")
    val n16x9620: n16x96205,
    @SerializedName("original_620")
    val original620: Original6205,
    @SerializedName("original_300")
    val original300: Original3005,
    @SerializedName("16x9tight_140")
    val n16x9tight140: n16x9tight1405,
    @SerializedName("square_140")
    val square140: Square1407,
    @SerializedName("16x9_780")
    val n16x9780: n16x97805,
    @SerializedName("square_60")
    val square60: Square605,
    @SerializedName("16x9_460")
    val n16x9460: n16x94605,
)

data class Square2205(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x99405(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x93005(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x96205(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original6205(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Original3005(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x9tight1405(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square1407(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x97805(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square605(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class n16x94605(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Categorization2(
    val dimensions: List<Dimension2>,
)

data class Dimension2(
    val entities: List<Entity2>,
    val name: String,
    val id: String,
)

data class Entity2(
    val name: String,
    val id: String,
)

data class Author6(
    @SerializedName("bio") val bio: Bio2,
    val authors: List<String>,
)

data class Bio2(
    val name: String?,
    @SerializedName("photo") val photo: Photo3,
    val id: String?,
    val biography: String?,
    val title: String?,
)

data class Photo3(
    val jsonurl: String?,
    val deck: String?,
    val alt: String?,
    val description: String?,
    val title: String?,
    val type: String?,
    val url: String?,
    @SerializedName("originalimage") val originalimage: Originalimage7?,
    @SerializedName("derivatives") val derivatives: Derivatives8?,
    val size: String?,
    val useoriginalimage: Boolean?,
    val originalimageurl: String?,
    val id: String?,
    val credit: String?,
    val headline: String?,
)

data class Originalimage7(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives8(
    @SerializedName("square_140")
    val square140: Square1408,
    @SerializedName("square_300")
    val square300: Square3004,
    @SerializedName("square_620")
    val square620: Square6204,
)

data class Square1408(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square3004(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square6204(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Intlink2(
    val jsonurl: String,
    val description: String,
    val id: String,
    val type: String,
    val title: String,
    val url: String,
    val embeddedtypes: String,
    val shareheadline: String,
)

data class Author7(
    val name: String,
    @SerializedName("photo") val photo: Photo4,
    val id: String,
    val biography: String,
    val type: String,
    val title: String,
    val url: String,
)

data class Photo4(
    val jsonurl: String,
    val deck: String,
    val alt: String,
    val description: String,
    val title: String,
    val type: String,
    val url: String,
    @SerializedName("originalimage") val originalimage: Originalimage8,
    @SerializedName("derivatives") val derivatives: Derivatives9,
    val size: String,
    val useoriginalimage: Boolean,
    val originalimageurl: String,
    val id: String,
    val credit: String,
    val headline: String,
)

data class Originalimage8(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Derivatives9(
    @SerializedName("square_140")
    val square140: Square1409,
    @SerializedName("square_300")
    val square300: Square3005,
    @SerializedName("square_620")
    val square620: Square6205,
)

data class Square1409(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square3005(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Square6205(
    val w: Long,
    val h: Long,
    val fileurl: String,
)

data class Item2(
    val id: Long,
    val title: String,
    val description: String,
    val source: String,
    val sourceId: String,
    val version: String,
    val publishedAt: Long,
    val readablePublishedAt: String,
    val updatedAt: Long,
    val readableUpdatedAt: String,
    val type: String,
    val active: Boolean,
    val draft: Boolean,
    val embedTypes: String,
    @SerializedName("typeAttributes") val typeAttributes: TypeAttributes5,
    @SerializedName("images") val images: Images4,
    val language: String,
)

data class TypeAttributes5(
    val url: String,
    val urlSlug: String,
    val deck: String,
    val imageSmall: String,
    val imageLarge: String,
    val imageAspects: String,
    val flag: String,
    @SerializedName("flags") val flags: Flags4,
    val displayComments: Boolean,
    val commentsSectionId: String,
    @SerializedName("trending") val trending: Trending4,
    val mediaDuration: Any?,
    val media: Any?,
    val mediaId: Any?,
    val mediaStreamType: Any?,
    val mediaCaptionUrl: Any?,
    val show: String,
    val showSlug: String,
    @SerializedName("body") val body: Body4,
    val sectionList: List<String>,
    val sectionLabels: List<String>,
    val categories: Any?,
    val contextualHeadlines: Any?,
    @SerializedName("headline") val headline: Headline3,
    @SerializedName("author") val author: Author8,
)

data class Flags4(
    val status: String,
    val label: String,
)

data class Trending4(
    val numViewers: Long,
    @SerializedName("numViewersSRS")
    val numViewersSrs: Long,
)

data class Body4(
    val containsAudio: Boolean,
    val containsVideo: Boolean,
    val containsPhotogallery: Boolean,
)

data class Headline3(
    val type: String,
    val mediaId: String,
)

data class Author8(
    val name: String,
    val display: String,
    val image: String,
)

data class Images4(
    @SerializedName("square_140")
    val square140: String,
)

data class Flags5(
    val status: String,
    val label: String,
)

data class Trending5(
    val numViewers: Long?,
    @SerializedName("numViewersSRS")
    val numViewersSrs: Long?,
)

data class Body5(
    val containsAudio: Boolean,
    val containsVideo: Boolean,
    val containsPhotogallery: Boolean,
)

data class Category(
    val id: Long,
    val name: String,
    val path: String,
    val type: String,
    val slug: String,
    val priority: Long,
    val priorityWhenInlined: Long,
    val metadata: Metadata,
    val image: String,
    val bannerImage: String,
    val contentArea: String?,
)

data class Metadata(
    val tracking: Tracking,
    val attribution: Attribution,
    val adHierarchy: String,
    val polopolyDeptName: String,
    val polopolyExternalId: String,
    val orderLineupId: String,
    val orderLineupSlug: String,
    val mpxCategoryName: String,
    val pageTitle: String,
    val pageDescription: String,
)

data class Tracking(
    val subsection1: String,
    val subsection2: String,
    val subsection3: String,
    val subsection4: String,
    val contentarea: String,
    val contenttype: String,
)

data class Attribution(
    val level1: String,
    val level2: String,
    val level3: String,
)

data class ContextualHeadline(
    val contextId: Any?,
    val headline: String,
    val contextualLineupSlug: String,
    val pubQueueId: String,
    val headlineType: String,
)

data class Author9(
    val name: String,
    val display: String,
    val image: String,
)

data class Headline4(
    val type: String,
    val mediaId: String,
)

data class Images5(
    @SerializedName("square_140")
    val square140: String,
)
