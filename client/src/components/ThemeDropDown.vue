<template>
    <!-- Theme dropdown -->
    <div :title="$t('change_theme')" :class="rtl ? 'dropdown' : 'dropdown dropdown-end'">
        <div tabindex="0"
             :class="rtl ? 'flex flex-row-reverse btn gap-1 normal-case btn-ghost' : 'btn gap-1 normal-case btn-ghost'">
            <svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 class="inline-block h-5 w-5 stroke-current md:h-6 md:w-6">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M7 21a4 4 0 01-4-4V5a2 2 0 012-2h4a2 2 0 012 2v12a4 4 0 01-4 4zm0 0h12a2 2 0 002-2v-4a2 2 0 00-2-2h-2.343M11 7.343l1.657-1.657a2 2 0 012.828 0l2.829 2.829a2 2 0 010 2.828l-8.486 8.485M7 17h.01"></path>
            </svg>
            <span class="hidden md:inline">{{ $t("theme") }}</span>
            <svg width="12px" height="12px"
                 class="ml-1 hidden h-3 w-3 fill-current opacity-60 sm:inline-block"
                 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 2048 2048">
                <path d="M1799 349l242 241-1017 1017L7 590l242-241 775 775 775-775z"></path>
            </svg>
        </div>
        <div
            class="dropdown-content bg-base-200 text-base-content rounded-t-box rounded-b-box top-px max-h-96 h-[70vh] w-52 overflow-y-auto shadow-2xl mt-16">
            <div class="grid grid-cols-1 gap-3 p-3" tabindex="0">
                <div v-for="theme in themes"
                     class="flex outline-base-content overflow-hidden rounded-lg outline-2 outline-offset-2"
                     :class="{'outline': theme === currentTheme}"
                     :title="$t(`themes.${theme}`)"
                     :data-set-theme="theme" data-act-class="outline">
                    <div :data-theme="theme"
                         @click="changeTheme(theme)"
                         class="bg-base-100 text-base-content w-full cursor-pointer font-sans">
                        <div class="grid grid-cols-5 grid-rows-3">
                            <div :class="{ 'flex-row-reverse': rtl}, { 'flex-row': !rtl}"
                                class="col-span-5 row-span-3 row-start-1 flex justify-between gap-1 py-3 px-4">
                                <div class="justify-start text-sm font-bold">{{ $t(`themes.${theme}`) }}</div>
                                <div class="flex flex-shrink-0 flex-wrap gap-1">
                                    <div class="bg-primary w-2 rounded"></div>
                                    <div class="bg-secondary w-2 rounded"></div>
                                    <div class="bg-accent w-2 rounded"></div>
                                    <div class="bg-neutral w-2 rounded"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import languages from "../assets/languages.json";
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: 'ThemeDropdown',
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl)
        }
    },
    data() {
        return {
            currentTheme: 'winter',
            themes: [
                'light',
                'dark',
                'cupcake',
                'bumblebee',
                'emerald',
                'corporate',
                'synthwave',
                'retro',
                'cyberpunk',
                'valentine',
                'halloween',
                'garden',
                'forest',
                'aqua',
                'lofi',
                'pastel',
                'fantasy',
                'wireframe',
                'black',
                'luxury',
                'dracula',
                'cmyk',
                'autumn',
                'business',
                'acid',
                'lemonade',
                'night',
                'coffee',
                'winter',
            ],
            languages: languages,
        }
    },
    methods: {
        changeTheme(theme) {
            this.currentTheme = theme;
            document.documentElement.setAttribute('data-theme', theme);
            localStorage.setItem('theme', theme);
        },

        getDefaultTheme() {
            const theme = localStorage.getItem('theme');
            if (theme) {
                this.currentTheme = theme;
            } else {
                // get OS theme
                const darkModeMediaQuery = window.matchMedia('(prefers-color-scheme: dark)');
                if (darkModeMediaQuery.matches) {
                    this.currentTheme = 'dracula';
                } else {
                    this.currentTheme = 'winter';
                }
            }
        },
    },
    mounted() {
        this.getDefaultTheme();
        document.documentElement.setAttribute('data-theme', this.currentTheme);
    }
}
</script>

<style scoped>

</style>