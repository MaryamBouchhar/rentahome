<template>
    <div>
        <div class="border-b border-t p-5">
            <h1
                class="text-2xl font-bold text-center"
            >
                {{ $t('wishlist.wishlist') }}
            </h1>
        </div>
        <!-- create a horizontal scroller -->
        <div :class="rtl ? 'flex-row-reverse' : 'flex-row'"
             class="flex overflow-x-scroll scrollbar-hide shadow-md p-2 mt-2 bg-base-100"
        >
            <div
                class="cursor-pointer m-1"
                @click="selectCategory(null)"
            >
                <span
                    class="badge badge-primary badge-lg w-fit"
                    :class="{'btn-outline': selectedCategory !== null}"
                >
                    {{ $t('wishlist.all') }}
                </span>
            </div>
            <!-- categoies badge -->
            <div
                class="cursor-pointer m-1"
                v-for="category in categories"
                :key="category.id"
                @click="selectCategory(category)"
            >
                    <span
                        class="badge badge-primary badge-lg w-fit"
                        :class="{'btn-outline': selectedCategory !== category.id}"
                    >
                        {{ category.name }}
                    </span>
            </div>
        </div>
    </div>

    <div
        v-if="isLoaded"
        class="flex flex-1"
    >
        <div
            :class="rtl ? 'flex-row-reverse' : 'flex-row'"
            class="flex grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 p-5"
        >
            <PropertyCard
                v-for="property in properties"
                :key="property.id"
            />
        </div>
    </div>
    <div
        v-else
    >
        <div class="flex flex-1 justify-center items-center">
            <div class="loader ease-linear rounded-full border-4 border-t-4 border-gray-200 h-12 w-12 mb-4"></div>
        </div>
    </div>
</template>

<script>

import {useStore} from 'vuex';
import {computed} from "vue";
import PropertyCard from "../components/PropertyCard.vue";

export default {
    name: 'Wishlist',
    components: {PropertyCard},
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl),
        }
    },
    data() {
        return {
            categories: [
                // categories & properties here
                {
                    id: 1,
                    name: 'Category_1',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 2,
                    name: 'Category_2',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 3,
                    name: 'Category_3',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 4,
                    name: 'Category_4',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 5,
                    name: 'Category_5',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 6,
                    name: 'Category_6',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 7,
                    name: 'Category_7',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 8,
                    name: 'Category_8',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                },
                {
                    id: 9,
                    name: 'Category_9',
                    properties: [
                        {id: 1, name: 'House 1'},
                        {id: 2, name: 'House 2'},
                        {id: 3, name: 'House 3'}
                    ]
                }
            ],
            selectedCategory: null,
            properties: [],
            isLoaded: false
        }
    },
    methods: {
        selectCategory(category) {
            this.isLoaded = false;
            this.selectedCategory = category ? category.id : null;
            this.getProperties();
        },
        getProperties() {
            if (this.selectedCategory === null) {
                let props = this.categories.map(category => category.properties).flat();
                this.isLoaded = true;
                this.properties = props;
            } else {
                let props = this.categories.find(category => category.id === this.selectedCategory).properties;
                this.isLoaded = true;
                this.properties = props;
            }
        }
    },
    mounted() {
        console.log(this.categories);
        console.log(this.selectedCategory);
        this.getProperties();
        this.isLoaded = true;
    }
}
</script>

<style scoped>
.overflow-x-scroll::-webkit-scrollbar {
    width: 5px;
    height: 5px;
}

.overflow-x-scroll::-webkit-scrollbar-thumb {
    @apply bg-primary;
}
</style>
