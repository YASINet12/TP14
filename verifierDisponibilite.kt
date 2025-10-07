package TP14_1

import kotlinx.coroutines.*

suspend fun verifierDisponibilite() {
    println("🔎 Vérification de la disponibilité des ingrédients...")
    delay(2000) // Simule 2 secondes d’attente
    println("Ingrédients disponibles.")
}

suspend fun preparerCommande() {
    println("Préparation de la commande en cours...")
    delay(5000) // Simule 5 secondes d’attente
    println(" Commande prête !")
}

suspend fun livrerRepas() = withContext(Dispatchers.IO) {
    println(" Livraison du repas en cours...")
    delay(3000) // Simule 3 secondes d’attente
    println(" Repas livré avec succès !")
}

fun main() = runBlocking {
    println("===  Simulation d'une commande de restaurant ===")

    // Étape 1 : Vérifier la disponibilité
    verifierDisponibilite()

    // Étape 2 : Préparer la commande
    val preparation = async { preparerCommande() }

    // Étape 3 : Livraison (commence après la préparation)
    preparation.await() // On attend que la préparation soit terminée
    val livraison = async { livrerRepas() }

    livraison.await()
    println(" Toutes les étapes de la commande sont terminées !")
}
