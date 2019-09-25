package com.github.thenicholaschen.knightsofthe4moons

import android.app.Application

class MyApplication: Application() {

    data class Scene(
        val title: String,
        val body: String,
        val actions: List<String>
    )

    companion object {
        // Constants
        val MAIN_MENU = "Main Menu"
        val TRY_AGAIN = "Try Again"
        val CONTINUE = "Continue"

        // Scenes
        val scenes: List<Scene> = listOf (
            // 0
            Scene (
                "Introduction",
                "You are Nicholas, the Sun Knight feared by many. " +
                        "You have the ability to harness the power of light from all the suns in the known universe. You are able to create cataclysm with a flick of your fingers. " +
                        "One day, you are seen on your Sun Horse, galloping away on the sand dunes of Arthabad.  " +
                        "You have been tasked by Wijaya, the Emperor of Arthabad to strategically infiltrate Erthos, and take down Sutedja, Emperor of Erthos, a known arch nemesis to Arthabad. " +
                        "Both dynasties have fought over territory for hundreds of years to no avail. " +
                        "Emperor Wijaya wishes to put a stop to this madness and depends on your wit to succeed.",


                    actions = listOf (
                    "Click anything to continue!",
                    "Yes, click anything to continue!",
                    "I told you already, click anything to continue!",
                    "Quick. Just click anything to continue!"
                )
            ),

            // 1
            Scene (
                "An Unusual Encounter",
                "As you gallop toward Erthos, you spot an old man, tattered and weak, " +
                        "walking in the opposite direction, far away from you. ",
                actions = listOf (
                    "Gallop toward him",
                    "Kill him",
                    "Ignore him, and gallop along",
                    "'Do you want a ride, sir?'"
                )
            ),

            // 2
            Scene (
                "Hel-lo th-there...",
                "'Hel-lo th-there', mutters the old man. 'Where are you hea-d-ed?' he cracks out weakly.",
                actions = listOf (
                    "'What are you doing in the middle of, nowhere?'",
                    "Ignore him and leave the old man to rot.",
                    "",
                    ""
                )
            ),

            // 3
            Scene (
                "Oh no...",
                "'You're not supposed to be here!', you say. You unwield your Sun Sword and attempt to decapitate the old man. 'Wa-ait, don't!' he yelps weakly.",
                actions = listOf (
                    "Slash him.",
                    "You have second thoughts, all of a sudden. You stop.",
                    "",
                    ""
                )
            ),

            // 4
            Scene (
                "The Heat Gets Worse.",
                "The only thing that sucks being a Sun Knight is, you overheat 99% of the time." +
                        "Given the sand dune situation, and the fact that you have been galloping for 34 straight hours," +
                        "You realize that the heat is starting to wear out on you. As you look around for a source of water, you spot a small settlement in the distance.",
                actions = listOf (
                    "Gallop that direction.",
                    "The mission is too important to stop for breaks.",
                    "",
                    ""
                )
            ),

            // 5
            Scene (
                "Feels Good To Be Generous",
                "'Wh-why, thank you, kind s-sir!' he mutters weakly, as he gets on the back saddle of your horse. You feel good. 'It's been a long time " +
                        "since I've been a good samaritan', you think to yourself. 'Where a-are y-ou he-aded?' he asks politely, but weakly.",
                actions = listOf (
                    "'Erthos, going on vacation!'",
                    "",
                    "",
                    ""
                )
            ),

            // 6
            Scene (
                "Riot",
                "'Si-ir, I have travelled a long way from Erthos. It's been 5 days and counting now. My family has been killed by the vengeful word of Sutedja, due to " +
                        "a riot between government factions that have been going on for weeks now.",
                actions = listOf (
                    "'Riot? What's the condition now?",
                    "",
                    "",
                    ""
                )
            ),

            // 7 -- badEnding1
            Scene (
                "Bad Ending: Always Keep Your Guard On",
                "Turns out that the old man has a radio on him. He signals on to the wall guards through his radio. " +
                        "A million poisonous Ertho-Spears come flying at you at light-speeds and you are instantly vaporized.",
                actions = listOf (
                    MAIN_MENU,
                    TRY_AGAIN,
                    "",
                    ""
                )
            ),

            // 8
            Scene (
                "An Open Opportunity",
                "'Still ba-ad.' he mutters. 'Sutedja, the Emperor, has been taken to the barracks of Wanadu for safety this morning by his guards, I heard." +
                        "'That bad, huh?' you reply. 'Ye-ah. The opposing force is to-o great.'",
                actions = listOf (
                    "'Wanadu? Where's that?'",
                    "'Thank you!' - and you gallop away.",
                    "'Thank you!' - and you slash him to pieces and keep his guts for dinner.",
                    ""
                )
            ),

            // 9
            Scene (
                "'South'",
                "'Sou-th of he-ere...' he coughs.",
                actions = listOf (
                    "'Thank you!' - and you gallop away.",
                    "You don't trust him, and you unwield your sword and slash him.",
                    "You spot a radio on him. But you" +
                            " play it smart. You have 2 apples. You give him one while saying 'Get some rest, sir. " +
                            "You need it. I'm going to Wanadu for some water. I'm thirsty.'",
                    ""
                )
            ),

            // 10
            Scene (
                "The Heat Gets Worse, And Something Doesn't Feel Right.",
                "The only thing that sucks being a Sun Knight is, you overheat 99% of the time." +
                        "Given the sand dune situation, and the fact that you have been galloping for 34 straight hours," +
                        "You realize that the heat is starting to wear out on you. As you look around for a source of water, you spot a small settlement in the distance.",
                actions = listOf (
                    "Gallop that direction.",
                    "The mission is too important to stop for breaks.",
                    "",
                    ""
                )
            ),

            // 11
            Scene (
                "Epiphany of Goodness",
                "Out of the 11,000,000 humans you have banished with your broadsword, something tells you that this time, you shouldn't do it. " +
                        "'Pl-ease don't, si-r!' he yells weakly.'",
                actions = listOf (
                    "'Don't worry. Today is not the day I kill. Do you want a ride?' You offer.",
                    "'What are you even doing here in the middle of nowhere?'",
                    "",
                    ""
                )
            ),

            // 12
            Scene (
                "Wanadu",
                "You see guards everywhere, guarding the settlement.",
                actions = listOf (
                    "'Do you guys have water or something? Coke is fine.'",
                    "You play it smart. 'The Emperor called for a Sun Knight to heal his thigh wounds through Sun Rays.'",
                    "",
                    ""
                )
            ),

            // 13
            Scene (
                "Wanadu",
                "You see guards everywhere, guarding the settlement. But something's not right.",
                actions = listOf (
                    "'Do you guys have water or something? Coke is fine.'",
                    "'Charge in'",
                    "You play it smart. 'The Emperor called for a Sun Knight to heal his thigh wounds through Sun Rays.'",
                    ""
                )
            ),

            // 14
            Scene (
                "Bad Ending: Thirst Quenching Death",
                "You may be the Sun Knight, but you still can get dehydration, you dumbass.",
                actions = listOf (
                    MAIN_MENU,
                    TRY_AGAIN,
                    "",
                    ""
                )
            ),

            // 15
            Scene (
                "Bad Ending: Mother's Intuition Hit You Too Late.",
                "You are killed by the guards upon eye-contact. The old man was an ex-guard of Erthos returning to his family in Lawgonski, North of Arthabad. " +
                        "He had a radio on him. You're just stupid.",
                actions = listOf (
                    MAIN_MENU,
                    TRY_AGAIN,
                    "",
                    ""
                )
            ),

            // 16
            Scene (
                "Normal Ending: You Got The Job Done, But...",
                "You killed everyone with your Sun Power, including the Emperor Sutedja. But the entire Erthos will know about the altercation, " +
                        "and it's only a matter of time when Arthabad will get it's karma...",
                actions = listOf (
                    MAIN_MENU,
                    TRY_AGAIN,
                    "",
                    ""
                )
            ),

            // 17
            Scene (
                "They Lead You To Him...",
                "As you whip out a genuine smile, the guards take you to Emperor Sutedja, enclosed behind the barrack doors. Through your experience, they appear to be sound-proof.",
                actions = listOf (
                    "You enter.",
                    "",
                    "",
                    ""
                )
            ),

            // 18
            Scene (
                "The Sutedja Encounter",
                "You see Emperor Sutedja, appearing to be in a deep state of sleep, lying comfortably. " +
                        "Minor wounds here and there, but breathing fine.",
                actions = listOf (
                    "You whip out your 'Nuclear Sun-Wrath Incarnation Ultimate' Move on his heart, finishing off with your 'Sun Dance, 4 Moon Apocalypse Move'",
                    "You spot his newly poured tea, into his teacup. You drizzle it with your 'Sunflare Vapor' poison up your sleeve, and exit.",
                    "",
                    ""
                )
            ),

            // 19
            Scene (
                "Bad Ending: You Messed Up, Bad. You Were So Close...",
                "You might be the Sun Knight and successfully killed Emperor Sutedja with your extremely cool moves, but you didn't live to tell the tale." +
                        "Don't let your ego take over. " +
                        "Your cool moves alerted all the guards in the compound, and they rained on you like fire-ants. " +
                        "They obliterated your very existence.",
                actions = listOf (
                    MAIN_MENU,
                    TRY_AGAIN,
                    "",
                    ""
                )
            ),

            // 20
            Scene (
                "Best Ending: You're Awesome, Nicholas.",
                "You exited the compound politely, after poisoning his drink." +
                        "One week after, Arthabad received news of the unknown death of Emperor Sutedja." +
                        "You were promoted to be the right-hand man of Wijaya. And you were given one virgin a day, for life, " +
                        "as a token of gratitude and honor. Congratulations.",
                actions = listOf (
                    MAIN_MENU,
                    TRY_AGAIN,
                    "",
                    ""
                )
            )

            )

        // Ending flags
        var badEnding1 = false
        var badEnding2 = false
        var badEnding3 = false
        var badEnding4 = false
        var normalEnding = false
        var bestEnding = false

        // Utils
        lateinit var currentDisplayedEnding: Scene
    }
}