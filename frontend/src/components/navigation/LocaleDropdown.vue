<template>
  <b-nav-item-dropdown :text="locales[$i18n.locale]" right>
    <b-dropdown-item
      v-for="(locale, key) in locales"
      :key="key"
      :active="$i18n.locale === key ? true : false"
      @click="setLocale(key)"
      >{{ locale }}</b-dropdown-item
    >
  </b-nav-item-dropdown>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "LocaleDropdown",
  data() {
    return {
      locales: {
        de: "Deutsch",
        en: "English",
      },
    };
  },
  methods: {
    setLocale(locale) {
      this.$i18n.locale = locale;
      localStorage.setItem("locale", locale);
    },
  },
});
</script>
