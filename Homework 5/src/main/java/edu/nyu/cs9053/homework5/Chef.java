package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef {

    private final SousChef sousChef;
    private final Oven oven;

    public Chef(int ovenIntialTemp) {
        oven = new Oven(ovenIntialTemp);
        sousChef = new SousChef(oven);
    }

    public static void main(String[] args) {

        Chef fiveStarChef = new Chef(300);
        Recipe[] listOfItems = new Recipe[] { new PotRoast(), new Baguette(), new RoastedSweetPotato(),
                new Baguette() };
        for (Recipe recipe : listOfItems) {

            fiveStarChef.sousChef.prepare(recipe, new RecipeReadyCallback() {

                @Override
                public void recipeReadyToCook(Recipe recipe) {
                    Timer timer = new Timer() {

                        @Override
                        public void update(Time unit, int value, int ovenTemperature) {
                            recipe.adjust(unit, value, ovenTemperature);
                        }

                    };
                    recipe.initializeFromOven(fiveStarChef.oven);
                    fiveStarChef.oven.cook(recipe, timer, true);
                    while (!recipe.isRecipeDone()) {
                        fiveStarChef.oven.cook(recipe, timer, false);
                    }
                    fiveStarChef.oven.takeOut(recipe);
                }
            });
        }
    }

}
