<template>
    <div>
        <div>
            <h1
                class="text-2xl font-bold text-center"
            >
                {{ $t('wishlist.wishlist') }}
            </h1>
        </div>
        <!-- create a horizontal scroller -->
        <div :class="rtl ? 'flex-row-reverse' : 'flex-row'"
             class="flex overflow-x-scroll scrollbar-hide shadow-md p-2 bg-base-100"
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

    <div class="flex flex-col flex-1">

    </div>
</template>

<script>

import {useStore} from 'vuex';
import {computed} from "vue";
import {reactive} from "vue";

export default {
    name: 'Wishlist',
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
            selectedCategory: null
        }
    },
    methods: {
        selectCategory(category) {
            this.selectedCategory = category ? category.id : null;
            console.log(this.selectedCategory);
        }
    },
    mounted() {
        console.log(this.categories);
        console.log(this.selectedCategory);
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
