package se.bylenny.enlist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratedListAdapter extends AbstractListAdapter<Item, GeneratedListItem> {

    private static final String[] PREFIXES = {
            "Dark",
            "Bright",
            "Smoky",
            "Wobbly",
            "Flaming",
            "Glowing",
            "Shiny",
            "Fuzzy",
            "Dripping",
            "Pulsating",
            "Simple",
            "Fancy",
            "Broken",
            "Hand crafted",
            "Home made",
            "Textured",
            "Dirty",
            "Small",
            "Tiny",
            "Wooden",
            "Iron",
            "Knitted",
            "Dreadful"
    };
    private static final String[] FIXES = {
            "boots",
            "hat",
            "tie",
            "gloves",
            "shoes",
            "sneakers",
            "bow tie",
            "glasses",
            "goggles",
            "snorkel",
            "necklace",
            "belt",
            "ring",
            "sweat band",
            "shorts",
            "t-short",
            "west",
            "cardigan",
            "shirt",
            "sandals",
            "Pants",
            "Wings",
            "tail",
            "handkerchief",
            "wrist watch"
    };
    private static final String[] SUFIXES = {
            "destruction",
            "damnation",
            "dexterity",
            "destiny",
            "dating",
            "pub crawling",
            "dancing",
            "stealth",
            "intelligence",
            "stamina",
            "wisdom",
            "stupidity",
            "singing",
            "doing the dishes",
            "charisma",
            "strength",
            "red stuff",
            "blue stuff",
            "anthroposophy"
    };
    private int count;
    private List<Item> items;
    private Random rnd = new Random();

    public GeneratedListAdapter(int count) {
        this.count = count;
        items = new ArrayList<>(count);
        populate();
    }

    private void populate() {
        for (int i = 0; i < count; i++) {
            float hue = (float) randomNatural(360);
            float[] color = {
                    hue,
                    1.0f,
                    0.5f
            };
            float[] background = {
                    color[0],
                    1.0f,
                    0.7f
            };
            items.add(new Item(Color.HSVToColor(color), Color.HSVToColor(background), createString()));
        }
    }

    private String createString() {
        return PREFIXES[randomNatural(PREFIXES.length)]
                + " "
                + FIXES[randomNatural(FIXES.length)]
                + " of "
                + SUFIXES[randomNatural(SUFIXES.length)];
    }

    private int randomNatural(int maxValue) {
        return Math.abs(rnd.nextInt()) % maxValue;
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public GeneratedListItem createViewHolder(ViewGroup parent) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new GeneratedListItem(view, context);
    }
}
