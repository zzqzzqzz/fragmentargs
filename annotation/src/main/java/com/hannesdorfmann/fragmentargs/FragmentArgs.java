package com.hannesdorfmann.fragmentargs;

import android.app.Fragment;

/**
 * @author Hannes Dorfmann
 */
public class FragmentArgs {

  public static final String AUTO_MAPPING_CLASS_NAME = "AutoFragmentArgInjector";
  public static final String AUTO_MAPPING_PACKAGE = "com.hannesdorfmann.fragmentargs";
  public static final String AUTO_MAPPING_QUALIFIED_CLASS =
      AUTO_MAPPING_PACKAGE + "." + AUTO_MAPPING_CLASS_NAME;

  private static FragmentArgsInjector autoMappingInjector;

  public static void injectArguments(Fragment fragment) {
    injectFromBundle(fragment);
  }

  // TODO support library

  static void injectFromBundle(Object target) {


    if (autoMappingInjector == null) {
      // Load the automapping class
      try {
        System.out.println("The first time calls forName:");
        Class<?> c = Class.forName(AUTO_MAPPING_QUALIFIED_CLASS);
        autoMappingInjector = (FragmentArgsInjector) c.newInstance();
      } catch (Exception e) {
        throw new RuntimeException(
            "Could not load the generated automapping class: " + e.getMessage(), e);
      }
    }

    autoMappingInjector.inject(target);
  }
}
