package com.wojciechmaciejewski.githubapirequester.testutils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class MyViewMatchers {

    public static Matcher<View> isVisible(final int expecteed) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is displayed on the screen to the user");
            }

            @Override
            public boolean matchesSafely(View view) {
                return view.getVisibility() == expecteed;
            }
        };
    }

    public static Matcher<View> hasRecyclerViewCorrectSize(final int expecteed) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("has recycler view correct number of elements");
            }

            @Override
            public boolean matchesSafely(View view) {
                return ((RecyclerView) view).getAdapter().getItemCount() == expecteed;
            }
        };
    }

    public static Matcher<View> isThereCorrectNumberOfChilds(final int expecteed) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("has list view correct number of elements");
            }

            @Override
            public boolean matchesSafely(View view) {

                return ((ViewGroup) view).getChildCount() == expecteed;
            }
        };
    }

    public static Matcher<View> isGoodAlphaLevel(final float expecteed) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Has element correct Alpha level");
            }

            @Override
            public boolean matchesSafely(View view) {
                return view.getAlpha() == expecteed;
            }
        };
    }


    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with " + childPosition + " child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }
}
