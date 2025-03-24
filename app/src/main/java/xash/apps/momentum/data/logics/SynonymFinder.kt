import java.io.File
import java.util.*
import kotlin.collections.HashMap

/**
 * A program to find synonyms of a word using a local thesaurus file.
 */
fun main() {
    println("Synonym Finder")
    println("==============")

    // Load the thesaurus
    val thesaurus = Thesaurus()
    println("Loading thesaurus...")

    try {
        thesaurus.loadFromFile("thesaurus.txt")
        println("Thesaurus loaded successfully with ${thesaurus.wordCount()} words.")
    } catch (e: Exception) {
        println("Error loading thesaurus: ${e.message}")
        println("Creating sample thesaurus for demonstration...")
        thesaurus.loadSampleData()
        println("Sample thesaurus created with ${thesaurus.wordCount()} words.")
    }

    // Interactive loop
    while (true) {
        print("\nEnter a word to find synonyms (or 'exit' to quit): ")
        val word = readLine()?.trim()?.lowercase() ?: ""

        if (word == "exit") {
            println("Goodbye!")
            break
        }

        if (word.isBlank()) {
            println("Error: Word cannot be empty.")
            continue
        }

        val synonyms = thesaurus.getSynonyms(word)

        if (synonyms.isEmpty()) {
            println("No synonyms found for '$word'.")
        } else {
            println("\nSynonyms for '$word':")
            synonyms.forEachIndexed { index, synonym ->
                println("${index + 1}. $synonym")
            }
        }
    }
}

/**
 * A class that represents a thesaurus with word-synonym mappings.
 */
class Thesaurus {
    private val wordMap = HashMap<String, MutableList<String>>()

    /**
     * Loads thesaurus data from a file.
     * Expected format: word:synonym1,synonym2,synonym3
     */
    fun loadFromFile(filePath: String) {
        File(filePath).forEachLine { line ->
            if (line.isNotBlank() && line.contains(":")) {
                val parts = line.split(":", limit = 2)
                val word = parts[0].trim().lowercase()
                val synonyms = parts[1].split(",").map { it.trim().lowercase() }

                wordMap[word] = synonyms.toMutableList()
            }
        }
    }

    /**
     * Loads some sample synonyms for demonstration purposes.
     */
    fun loadSampleData() {
        // Sample data
        addSynonyms("happy", listOf("joyful", "glad", "delighted", "cheerful", "merry"))
        addSynonyms("sad", listOf("unhappy", "sorrowful", "dejected", "downcast", "gloomy"))
        addSynonyms("big", listOf("large", "huge", "enormous", "gigantic", "vast"))
        addSynonyms("small", listOf("little", "tiny", "minute", "diminutive", "compact"))
        addSynonyms("good", listOf("excellent", "fine", "superior", "quality", "worthy"))
        addSynonyms("bad", listOf("poor", "inferior", "substandard", "defective", "awful"))
        addSynonyms("beautiful", listOf("pretty", "attractive", "gorgeous", "stunning", "lovely"))
        addSynonyms("ugly", listOf("unattractive", "hideous", "unsightly", "homely", "plain"))
        addSynonyms("fast", listOf("quick", "rapid", "swift", "speedy", "hasty"))
        addSynonyms("slow", listOf("sluggish", "unhurried", "leisurely", "gradual", "plodding"))

        // Add bidirectional synonyms for the sample data
        for ((word, synonymList) in HashMap(wordMap)) {
            for (synonym in synonymList) {
                if (!wordMap.containsKey(synonym)) {
                    wordMap[synonym] = mutableListOf(word)
                } else if (word !in wordMap[synonym]!!) {
                    wordMap[synonym]!!.add(word)
                }
            }
        }
    }

    /**
     * Add a word and its synonyms to the thesaurus.
     */
    fun addSynonyms(word: String, synonyms: List<String>) {
        wordMap[word.lowercase()] = synonyms.map { it.lowercase() }.toMutableList()
    }

    /**
     * Get synonyms for a word.
     */
    fun getSynonyms(word: String): List<String> {
        return wordMap[word.lowercase()]?.sorted() ?: emptyList()
    }

    /**
     * Get the number of words in the thesaurus.
     */
    fun wordCount(): Int {
        return wordMap.size
    }
}

/**
 * A utility function to create a thesaurus file with sample data.
 * Call this function to generate a sample thesaurus file.
 */
fun createSampleThesaurusFile() {
    val sampleData = """
        happy: joyful, glad, delighted, cheerful, merry
        sad: unhappy, sorrowful, dejected, downcast, gloomy
        big: large, huge, enormous, gigantic, vast
        small: little, tiny, minute, diminutive, compact
        good: excellent, fine, superior, quality, worthy
        bad: poor, inferior, substandard, defective, awful
        beautiful: pretty, attractive, gorgeous, stunning, lovely
        ugly: unattractive, hideous, unsightly, homely, plain
        fast: quick, rapid, swift, speedy, hasty
        slow: sluggish, unhurried, leisurely, gradual, plodding
    """.trimIndent()

    File("thesaurus.txt").writeText(sampleData)
    println("Sample thesaurus file created at 'thesaurus.txt'")
}