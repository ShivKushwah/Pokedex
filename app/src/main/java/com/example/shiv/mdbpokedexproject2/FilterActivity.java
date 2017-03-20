package com.example.shiv.mdbpokedexproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Setup buttons and fields
        final EditText min_Attack = (EditText) findViewById(R.id.editText2);
        final EditText min_Defense = (EditText) findViewById(R.id.editText4);
        final EditText min_health = (EditText) findViewById(R.id.editText5);
        Button button2 = (Button) findViewById(R.id.button2);
        ToggleButton dragon = (ToggleButton) findViewById(R.id.toggleButtonDragon);
        ToggleButton fairy = (ToggleButton) findViewById(R.id.toggleButtonFairy);
        ToggleButton normal = (ToggleButton) findViewById(R.id.toggleButtonNormal);
        ToggleButton fire = (ToggleButton) findViewById(R.id.toggleButtonFire);
        ToggleButton water = (ToggleButton) findViewById(R.id.toggleButtonWater);
        ToggleButton grass = (ToggleButton) findViewById(R.id.toggleButtonGrass);
        ToggleButton electric = (ToggleButton) findViewById(R.id.toggleButtonElectric);
        ToggleButton ice = (ToggleButton) findViewById(R.id.toggleButtonIce);
        ToggleButton ground = (ToggleButton) findViewById(R.id.toggleButtonGround);
        ToggleButton flying = (ToggleButton) findViewById(R.id.toggleButtonFlying);
        ToggleButton poison = (ToggleButton) findViewById(R.id.toggleButtonPoison);
        ToggleButton fighting = (ToggleButton) findViewById(R.id.toggleButtonFighting);
        ToggleButton psychic = (ToggleButton) findViewById(R.id.toggleButtonPsychic);
        ToggleButton dark = (ToggleButton) findViewById(R.id.toggleButtonDark);
        ToggleButton rock = (ToggleButton) findViewById(R.id.toggleButtonRock);
        ToggleButton bug = (ToggleButton) findViewById(R.id.toggleButtonBug);
        ToggleButton ghost = (ToggleButton) findViewById(R.id.toggleButtonGhost);
        ToggleButton steel = (ToggleButton) findViewById(R.id.toggleButtonSteel);
        ToggleButton allTypes = (ToggleButton) findViewById(R.id.toggleButtonAllTypes);







        min_Attack.setText("" + MainActivity.min_attack);
        min_Defense.setText("" + MainActivity.min_defense);
        min_health.setText("" + MainActivity.min_health);



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.min_attack = Integer.parseInt(min_Attack.getText().toString());
                } catch (Throwable e) {
                    MainActivity.min_attack = 0;
                }
                try {
                    MainActivity.min_defense = Integer.parseInt(min_Defense.getText().toString());
                } catch (Throwable e) {
                    MainActivity.min_defense = 0;
                }
                try {
                    MainActivity.min_health = Integer.parseInt(min_health.getText().toString());
                } catch (Throwable e) {
                    MainActivity.min_health = 0;
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                Toast toast = Toast.makeText(getApplicationContext(), "Filter Updated!", Toast.LENGTH_SHORT);
                toast.show();

                startActivity(intent);
            }
        });

        MainActivity.displayAllTypes = false;
        //Setting up all toggle buttons
        dragon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Dragon");

                } else {
                   try
                   {
                       MainActivity.type.remove("Dragon");
                   } catch (Throwable e)
                   {

                   }
                }
            }
        });
        fairy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Fairy");

                } else {
                    try
                    {
                        MainActivity.type.remove("Fairy");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Normal");

                } else {
                    try
                    {
                        MainActivity.type.remove("Normal");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        fire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Fire");

                } else {
                    try
                    {
                        MainActivity.type.remove("Fire");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Water");

                } else {
                    try
                    {
                        MainActivity.type.remove("Water");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        grass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Grass");

                } else {
                    try
                    {
                        MainActivity.type.remove("Grass");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        ice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Ice");

                } else {
                    try
                    {
                        MainActivity.type.remove("Ice");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        ground.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Ground");

                } else {
                    try
                    {
                        MainActivity.type.remove("Ground");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        flying.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Flying");

                } else {
                    try
                    {
                        MainActivity.type.remove("Flying");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        poison.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Poison");

                } else {
                    try
                    {
                        MainActivity.type.remove("Poison");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        fighting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Fighting");

                } else {
                    try
                    {
                        MainActivity.type.remove("Fighting");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        psychic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Psychic");

                } else {
                    try
                    {
                        MainActivity.type.remove("Psychic");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Dark");

                } else {
                    try
                    {
                        MainActivity.type.remove("Dark");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        rock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Rock");

                } else {
                    try
                    {
                        MainActivity.type.remove("Rock");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        bug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Bug");

                } else {
                    try
                    {
                        MainActivity.type.remove("Bug");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        ghost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Ghost");

                } else {
                    try
                    {
                        MainActivity.type.remove("Ghost");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });
        steel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.type.add("Steel");

                } else {
                    try
                    {
                        MainActivity.type.remove("Steel");
                    } catch (Throwable e)
                    {

                    }
                }
            }
        });

        allTypes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   MainActivity.displayAllTypes = true;

                } else {
                    MainActivity.displayAllTypes = false;
                }
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.type = new ArrayList<>();

        Toast toast2 = Toast.makeText(getApplicationContext(), "Type Filters Reset", Toast.LENGTH_SHORT);
        toast2.show();

    }
}
