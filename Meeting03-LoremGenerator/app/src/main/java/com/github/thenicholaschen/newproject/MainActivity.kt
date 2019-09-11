package com.github.thenicholaschen.newproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil //so that binding is backward compatible
import com.github.thenicholaschen.newproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //gotta do this to do binding
    private lateinit var binding: ActivityMainBinding

    //Declaring all the Ipsums
    private val loremIpsum : Ipsum = Ipsum("loremIpsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
            "commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
            "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
    private val cupcakeIpsum : Ipsum = Ipsum("cupcakeIpsum", "Jelly-o gummi bears wafer. Oat cake cupcake bonbon toffee. " +
            "Jelly tiramisu gummi bears jelly beans dragée dragée cupcake fruitcake. Jelly beans pastry toffee halvah caramels. Jujubes chocolate cake " +
            "croissant powder marshmallow lemon drops jujube8s gingerbread gingerbread. Gummi bears macaroon ice cream jujubes gingerbread sesame snaps sweet tootsie roll. " +
            "Toffee candy donut chupa chups sugar plum liquorice muffin tiramisu. Jujubes icing croissant sweet gummi bears jelly beans gummies liquorice. " +
            "Sweet roll fruitcake candy gummies marshmallow. Sweet roll topping pastry oat cake chocolate cake. Oat cake jelly beans marshmallow jelly-o. " +
            "Bear claw chocolate carrot cake chocolate cake marzipan pastry chocolate danish gummies. Sweet roll topping marshmallow.")
    private val samuelLIpsum : Ipsum = Ipsum("samuelLIpsum", "Well, the way they make shows is, they make one show. That show's called a pilot. Then they show that show to the people who make shows, and on the strength of that one show they decide if they're going to make more shows. Some pilots get picked and become television programs. " +
            "Some don't, become nothing. She starred in one of the ones that became nothing.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.loremIpsum = loremIpsum
        binding.cupcakeIpsum = cupcakeIpsum
        binding.samuelLIpsum = samuelLIpsum

        //Initializing the spinner
        val adapter = ArrayAdapter.createFromResource(this, R.array.lorem_types, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.loremSpinner.adapter = adapter


        binding.loremSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val currentType: String = binding.loremSpinner.getItemAtPosition(position).toString()
                when (currentType) {
                    "Lorem Ipsum" -> {
                        binding.titleText.text = loremIpsum.type
                        binding.loremText.text = loremIpsum.content
                    }
                    "Cupcake Ipsum" -> {
                        binding.titleText.text = cupcakeIpsum.type
                        binding.loremText.text = cupcakeIpsum.content
                    }
                    "Samuel L Ipsum" -> {
                        binding.titleText.text = samuelLIpsum.type
                        binding.loremText.text = samuelLIpsum.content
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("LoremGenerator :: ","onNothingSelected() is called")
            }
        }

        binding.creatorButton.setOnClickListener {
            pickCreatorName(it)
        }
        binding.creatorText.setOnClickListener {
            changeCreatorName(it)
        }
    }

    private fun pickCreatorName(view: View) {
        //change text
        //binding.creatorText.text = binding.creatorFill.text.toString() //they're not the same thing, editable is like a string but not.

        //easiest way
        binding.apply {
            creatorText.text = creatorFill.text.toString()
            //hide and show required views
            creatorFill.visibility = View.GONE
            creatorButton.visibility = View.GONE
            creatorText.visibility = View.VISIBLE
        }
        //view.visibility = View.GONE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun changeCreatorName(view: View) {
        //assign views
        view.visibility = View.GONE

        binding.apply {
            creatorText.text = creatorFill.text.toString()
            //hide and show required views
            creatorFill.visibility = View.VISIBLE
            creatorButton.visibility = View.VISIBLE
            view.visibility = View.GONE
        }

        binding.creatorFill.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.creatorFill, 0)
    }
}
