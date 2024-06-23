// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: elenor/data/user_preferences.proto

// Protobuf Java Version: 4.26.0
package com.fanimo.ecommerce.core.datastore;

public interface UserPreferencesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UserPreferences)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>repeated int32 deprecated_int_followed_topic_ids = 1;</code>
   * @return A list containing the deprecatedIntFollowedTopicIds.
   */
  java.util.List<java.lang.Integer> getDeprecatedIntFollowedTopicIdsList();
  /**
   * <code>repeated int32 deprecated_int_followed_topic_ids = 1;</code>
   * @return The count of deprecatedIntFollowedTopicIds.
   */
  int getDeprecatedIntFollowedTopicIdsCount();
  /**
   * <code>repeated int32 deprecated_int_followed_topic_ids = 1;</code>
   * @param index The index of the element to return.
   * @return The deprecatedIntFollowedTopicIds at the given index.
   */
  int getDeprecatedIntFollowedTopicIds(int index);

  /**
   * <code>int32 topicChangeListVersion = 3;</code>
   * @return The topicChangeListVersion.
   */
  int getTopicChangeListVersion();

  /**
   * <code>int32 authorChangeListVersion = 4;</code>
   * @return The authorChangeListVersion.
   */
  int getAuthorChangeListVersion();

  /**
   * <code>int32 newsResourceChangeListVersion = 6;</code>
   * @return The newsResourceChangeListVersion.
   */
  int getNewsResourceChangeListVersion();

  /**
   * <code>repeated int32 deprecated_int_followed_author_ids = 7;</code>
   * @return A list containing the deprecatedIntFollowedAuthorIds.
   */
  java.util.List<java.lang.Integer> getDeprecatedIntFollowedAuthorIdsList();
  /**
   * <code>repeated int32 deprecated_int_followed_author_ids = 7;</code>
   * @return The count of deprecatedIntFollowedAuthorIds.
   */
  int getDeprecatedIntFollowedAuthorIdsCount();
  /**
   * <code>repeated int32 deprecated_int_followed_author_ids = 7;</code>
   * @param index The index of the element to return.
   * @return The deprecatedIntFollowedAuthorIds at the given index.
   */
  int getDeprecatedIntFollowedAuthorIds(int index);

  /**
   * <code>bool has_done_int_to_string_id_migration = 8;</code>
   * @return The hasDoneIntToStringIdMigration.
   */
  boolean getHasDoneIntToStringIdMigration();

  /**
   * <code>repeated string deprecated_followed_topic_ids = 9;</code>
   * @return A list containing the deprecatedFollowedTopicIds.
   */
  java.util.List<java.lang.String>
      getDeprecatedFollowedTopicIdsList();
  /**
   * <code>repeated string deprecated_followed_topic_ids = 9;</code>
   * @return The count of deprecatedFollowedTopicIds.
   */
  int getDeprecatedFollowedTopicIdsCount();
  /**
   * <code>repeated string deprecated_followed_topic_ids = 9;</code>
   * @param index The index of the element to return.
   * @return The deprecatedFollowedTopicIds at the given index.
   */
  java.lang.String getDeprecatedFollowedTopicIds(int index);
  /**
   * <code>repeated string deprecated_followed_topic_ids = 9;</code>
   * @param index The index of the element to return.
   * @return The deprecatedFollowedTopicIds at the given index.
   */
  com.google.protobuf.ByteString
      getDeprecatedFollowedTopicIdsBytes(int index);

  /**
   * <code>repeated string deprecated_followed_author_ids = 10;</code>
   * @return A list containing the deprecatedFollowedAuthorIds.
   */
  java.util.List<java.lang.String>
      getDeprecatedFollowedAuthorIdsList();
  /**
   * <code>repeated string deprecated_followed_author_ids = 10;</code>
   * @return The count of deprecatedFollowedAuthorIds.
   */
  int getDeprecatedFollowedAuthorIdsCount();
  /**
   * <code>repeated string deprecated_followed_author_ids = 10;</code>
   * @param index The index of the element to return.
   * @return The deprecatedFollowedAuthorIds at the given index.
   */
  java.lang.String getDeprecatedFollowedAuthorIds(int index);
  /**
   * <code>repeated string deprecated_followed_author_ids = 10;</code>
   * @param index The index of the element to return.
   * @return The deprecatedFollowedAuthorIds at the given index.
   */
  com.google.protobuf.ByteString
      getDeprecatedFollowedAuthorIdsBytes(int index);

  /**
   * <code>repeated string deprecated_bookmarked_news_resource_ids = 11;</code>
   * @return A list containing the deprecatedBookmarkedNewsResourceIds.
   */
  java.util.List<java.lang.String>
      getDeprecatedBookmarkedNewsResourceIdsList();
  /**
   * <code>repeated string deprecated_bookmarked_news_resource_ids = 11;</code>
   * @return The count of deprecatedBookmarkedNewsResourceIds.
   */
  int getDeprecatedBookmarkedNewsResourceIdsCount();
  /**
   * <code>repeated string deprecated_bookmarked_news_resource_ids = 11;</code>
   * @param index The index of the element to return.
   * @return The deprecatedBookmarkedNewsResourceIds at the given index.
   */
  java.lang.String getDeprecatedBookmarkedNewsResourceIds(int index);
  /**
   * <code>repeated string deprecated_bookmarked_news_resource_ids = 11;</code>
   * @param index The index of the element to return.
   * @return The deprecatedBookmarkedNewsResourceIds at the given index.
   */
  com.google.protobuf.ByteString
      getDeprecatedBookmarkedNewsResourceIdsBytes(int index);

  /**
   * <code>bool has_done_list_to_map_migration = 12;</code>
   * @return The hasDoneListToMapMigration.
   */
  boolean getHasDoneListToMapMigration();

  /**
   * <pre>
   * Each map is used to store a set of string IDs. The bool has no meaning, but proto3 doesn't
   * have a Set type so this is the closest we can get to a Set.
   * </pre>
   *
   * <code>map&lt;string, bool&gt; followed_topic_ids = 13;</code>
   */
  int getFollowedTopicIdsCount();
  /**
   * <pre>
   * Each map is used to store a set of string IDs. The bool has no meaning, but proto3 doesn't
   * have a Set type so this is the closest we can get to a Set.
   * </pre>
   *
   * <code>map&lt;string, bool&gt; followed_topic_ids = 13;</code>
   */
  boolean containsFollowedTopicIds(
      java.lang.String key);
  /**
   * Use {@link #getFollowedTopicIdsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.Boolean>
  getFollowedTopicIds();
  /**
   * <pre>
   * Each map is used to store a set of string IDs. The bool has no meaning, but proto3 doesn't
   * have a Set type so this is the closest we can get to a Set.
   * </pre>
   *
   * <code>map&lt;string, bool&gt; followed_topic_ids = 13;</code>
   */
  java.util.Map<java.lang.String, java.lang.Boolean>
  getFollowedTopicIdsMap();
  /**
   * <pre>
   * Each map is used to store a set of string IDs. The bool has no meaning, but proto3 doesn't
   * have a Set type so this is the closest we can get to a Set.
   * </pre>
   *
   * <code>map&lt;string, bool&gt; followed_topic_ids = 13;</code>
   */

  boolean getFollowedTopicIdsOrDefault(
      java.lang.String key,
      boolean defaultValue);
  /**
   * <pre>
   * Each map is used to store a set of string IDs. The bool has no meaning, but proto3 doesn't
   * have a Set type so this is the closest we can get to a Set.
   * </pre>
   *
   * <code>map&lt;string, bool&gt; followed_topic_ids = 13;</code>
   */

  boolean getFollowedTopicIdsOrThrow(
      java.lang.String key);

  /**
   * <code>map&lt;string, bool&gt; followed_author_ids = 14;</code>
   */
  int getFollowedAuthorIdsCount();
  /**
   * <code>map&lt;string, bool&gt; followed_author_ids = 14;</code>
   */
  boolean containsFollowedAuthorIds(
      java.lang.String key);
  /**
   * Use {@link #getFollowedAuthorIdsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.Boolean>
  getFollowedAuthorIds();
  /**
   * <code>map&lt;string, bool&gt; followed_author_ids = 14;</code>
   */
  java.util.Map<java.lang.String, java.lang.Boolean>
  getFollowedAuthorIdsMap();
  /**
   * <code>map&lt;string, bool&gt; followed_author_ids = 14;</code>
   */

  boolean getFollowedAuthorIdsOrDefault(
      java.lang.String key,
      boolean defaultValue);
  /**
   * <code>map&lt;string, bool&gt; followed_author_ids = 14;</code>
   */

  boolean getFollowedAuthorIdsOrThrow(
      java.lang.String key);

  /**
   * <code>map&lt;string, bool&gt; bookmarked_news_resource_ids = 15;</code>
   */
  int getBookmarkedNewsResourceIdsCount();
  /**
   * <code>map&lt;string, bool&gt; bookmarked_news_resource_ids = 15;</code>
   */
  boolean containsBookmarkedNewsResourceIds(
      java.lang.String key);
  /**
   * Use {@link #getBookmarkedNewsResourceIdsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.Boolean>
  getBookmarkedNewsResourceIds();
  /**
   * <code>map&lt;string, bool&gt; bookmarked_news_resource_ids = 15;</code>
   */
  java.util.Map<java.lang.String, java.lang.Boolean>
  getBookmarkedNewsResourceIdsMap();
  /**
   * <code>map&lt;string, bool&gt; bookmarked_news_resource_ids = 15;</code>
   */

  boolean getBookmarkedNewsResourceIdsOrDefault(
      java.lang.String key,
      boolean defaultValue);
  /**
   * <code>map&lt;string, bool&gt; bookmarked_news_resource_ids = 15;</code>
   */

  boolean getBookmarkedNewsResourceIdsOrThrow(
      java.lang.String key);

  /**
   * <code>map&lt;string, bool&gt; viewed_news_resource_ids = 20;</code>
   */
  int getViewedNewsResourceIdsCount();
  /**
   * <code>map&lt;string, bool&gt; viewed_news_resource_ids = 20;</code>
   */
  boolean containsViewedNewsResourceIds(
      java.lang.String key);
  /**
   * Use {@link #getViewedNewsResourceIdsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.Boolean>
  getViewedNewsResourceIds();
  /**
   * <code>map&lt;string, bool&gt; viewed_news_resource_ids = 20;</code>
   */
  java.util.Map<java.lang.String, java.lang.Boolean>
  getViewedNewsResourceIdsMap();
  /**
   * <code>map&lt;string, bool&gt; viewed_news_resource_ids = 20;</code>
   */

  boolean getViewedNewsResourceIdsOrDefault(
      java.lang.String key,
      boolean defaultValue);
  /**
   * <code>map&lt;string, bool&gt; viewed_news_resource_ids = 20;</code>
   */

  boolean getViewedNewsResourceIdsOrThrow(
      java.lang.String key);

  /**
   * <code>.ThemeBrandProto theme_brand = 16;</code>
   * @return The enum numeric value on the wire for themeBrand.
   */
  int getThemeBrandValue();
  /**
   * <code>.ThemeBrandProto theme_brand = 16;</code>
   * @return The themeBrand.
   */
  com.fanimo.ecommerce.core.datastore.ThemeBrandProto getThemeBrand();

  /**
   * <code>.DarkThemeConfigProto dark_theme_config = 17;</code>
   * @return The enum numeric value on the wire for darkThemeConfig.
   */
  int getDarkThemeConfigValue();
  /**
   * <code>.DarkThemeConfigProto dark_theme_config = 17;</code>
   * @return The darkThemeConfig.
   */
  com.fanimo.ecommerce.core.datastore.DarkThemeConfigProto getDarkThemeConfig();

  /**
   * <code>bool should_hide_onboarding = 18;</code>
   * @return The shouldHideOnboarding.
   */
  boolean getShouldHideOnboarding();

  /**
   * <code>bool use_dynamic_color = 19;</code>
   * @return The useDynamicColor.
   */
  boolean getUseDynamicColor();

  /**
   * <code>bool is_logged_in = 21;</code>
   * @return The isLoggedIn.
   */
  boolean getIsLoggedIn();
}
