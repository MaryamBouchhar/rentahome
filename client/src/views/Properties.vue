<template>
    <div>
        <h1 class="w-full text-center font-bold text-2xl my-5">
            Properties
        </h1>
        <!-- TODO: Add filter options in sidebar -->
        <div class="flex flex-col lg:flex-row">
            <div class="filter-bar w-full lg:w-1/4 px-4 rounded-lg border border-gray-400 py-3 ml-2 shadow-lg">
                <!-- Search -->
                <div class="form-control mb-2">
                    <div class="input-group">
                        <input type="text" placeholder="Search…" class="input input-bordered" v-model="filter.search"/>
                        <button class="btn btn-square" @click="filterBySearch">
                            <i class='bx bx-search bx-sm'></i>
                        </button>
                    </div>
                </div>

                <!-- Filter by type -->
                <label class="label font-bold">
                    <span class="label-text">Filter by category</span>
                </label>
                <select class="select select-primary w-full max-w-xs" v-model="filter.category"
                        @change="filterByCategory">
                    <option disabled selected>Filter by category</option>
                    <option v-for="category in property_categories" :key="category">{{ category }}</option>
                </select>
                <!-- Filter by City -->
                <label class="label font-bold">
                    <span class="label-text">Filter by city</span>
                </label>
                <select class="select select-primary w-full max-w-xs" v-model="filter.city" @change="filterByCity">
                    <option disabled selected>Filter by city</option>
                    <option v-for="city in cities" :key="city">{{ city }}</option>
                </select>

                <!-- Filter by price -->
                <label class="label font-bold">
                    <span class="label-text">Filter by price</span>
                </label>
                <div class="flex justify-between gap-2">
                    <input type="number" placeholder="Min" min="10" class="input input-bordered w-1/2"
                           v-model="filter.price.min_price"/>
                    <input type="number" placeholder="Max" min="10" class="input input-bordered w-1/2"
                           v-model="filter.price.max_price"/>
                </div>

                <!-- Filter by rating -->
                <label class="label font-bold">
                    <span class="label-text">Filter by rating</span>
                </label>
                <div class="rating mb-3">
                    <input type="radio" name="rating-2" :checked="index === selected" @change="select(index)"
                           class="mask mask-star-2 bg-orange-400" v-for="(star, index) in stars" :key="index"/>
                </div>
                <button class="btn btn-wide" @click="filterByPriceRange">OK</button>
            </div>
            <div class="flex flex-col w-full content-center mx-4">
                <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-2 flex justify-center mx-auto"
                     v-if="properties.length > 0">
                    <PropertyCard v-for="property in properties" :key="property.id" :property="property"/>
                </div>
                <div class="alert alert-warning shadow-lg flex flex-col items-center justify-center" v-else>
                    <div>
                        <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6" fill="none"
                             viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                  d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"/>
                        </svg>
                        <span>No properties found, please search again.</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <Footer/>
</template>

<script>
import {ref, onMounted} from 'vue'
import 'boxicons'
import PropertyCard from "../components/PropertyCard.vue";
import Footer from "../components/Footer.vue";
import axios from 'axios';

export default {
    name: "Properties",
    components: {PropertyCard},

    data() {
        return {
            properties: [],
            selectedProperty: {},
            isModalActive: false,
            filter: {
                search: "",
                category: "",
                city: "",
                price: {
                    min_price: 0,
                    max_price: 0
                },
                rating: 0
            },
            PROPERTY_API_BASE_URL: `http://localhost:8080/manage-properties/properties`,
            property_categories: [
                'House',
                'Apartment',
                'Condo',
                'Townhouse',
                'Villa',
                'Bungalow',
            ],
            cities: [],
            stars: [1, 2, 3, 4, 5]
        };
    },
    methods: {
        async getProperties() {
            await axios.get(this.PROPERTY_API_BASE_URL)
                .then(response => {
                    this.properties = response.data
                    this.properties.forEach(property => {
                        property.publish_date = new Date(property.publish_date).toLocaleDateString();
                    })
                    console.log("Properties: ", this.properties)
                    //get each property's image
                    this.getPropertyImage();
                }).catch(error => console.log(error))
            console.log(this.properties);
        },
        async getPropertyImage() {
            //get each property's image
            for (let i = 0; i < this.properties.length; i++) {
                await axios.get('http://localhost:8080/uploads/properties/' + this.properties[i].id + '/first', {responseType: 'arraybuffer'})
                    .then(response => {
                        console.log("Property Image: ", response.data)
                        const imageBlob = new Blob([response.data], {type: 'image/jpeg'});
                        this.properties[i].image = URL.createObjectURL(imageBlob)
                    })
                    .catch(error => {
                        console.log(error)
                    })
            }
            console.log("Properties: ", this.properties)
        },
        async filterByPriceRange() {
            await axios.post('http://localhost:8080/manage-properties/filter-by-price', this.filter.price, {
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(response => {
                this.properties = response.data
                this.properties.forEach(property => {
                    property.publish_date = new Date(property.publish_date).toLocaleDateString();
                })
                console.log("Properties: ", this.properties)
                //get each property's image
                this.getPropertyImage();
            }).catch(error => console.log(error))
        },
        async filterByCategory() {
            const category = this.filter.category
            axios.post('http://localhost:8080/manage-properties/filter-by-category', category, {
                headers: {
                    'Content-Type': 'text/plain',
                }
            }).then(response => {
                this.properties = response.data
                this.properties.forEach(property => {
                    property.publish_date = new Date(property.publish_date).toLocaleDateString();
                })
                console.log("Properties: ", this.properties)
                //get each property's image
                this.getPropertyImage();
            }).catch(error => console.log(error))
        },
        async filterByCity() {
            const city = this.filter.city
            await axios.post('http://localhost:8080/manage-properties/filter-by-city', city, {
                headers: {
                    'Content-Type': 'text/plain',
                }
            }).then(response => {
                this.properties = response.data
                this.properties.forEach(property => {
                    property.publish_date = new Date(property.publish_date).toLocaleDateString();
                })
                console.log("Properties: ", this.properties)
                //get each property's image
                this.getPropertyImage();
            }).catch(error => console.log(error))
        },
        async filterByRating() {
            const formDate = new FormData();
            formDate.append('rating', this.filter.rating)
            await axios.post('http://localhost:8080/manage-properties/filter-by-rating', formDate)
                .then(response => {
                    this.properties = response.data
                    this.properties.forEach(property => {
                        property.publish_date = new Date(property.publish_date).toLocaleDateString();
                    })
                    console.log("Properties: ", this.properties)
                    //get each property's image
                    this.getPropertyImage();
                }).catch(error => console.log(error))
        },
        async filterBySearch() {
            const search = this.filter.search
            await axios.post('http://localhost:8080/manage-properties/filter-by-search', search, {
                headers: {
                    'Content-Type': 'text/plain',
                }
            }).then(response => {
                this.properties = response.data
                this.properties.forEach(property => {
                    property.publish_date = new Date(property.publish_date).toLocaleDateString();
                })
                console.log("Properties: ", this.properties)
                //get each property's image
                this.getPropertyImage();
            }).catch(error => console.log(error))
        },
        async getAllCities() {
            await axios.get('http://localhost:8080/manage-properties/cities')
                .then(response => {
                    this.cities = response.data
                    console.log("Cities: ", this.cities)
                }).catch(error => console.log(error))
        },
        select(index) {
            this.selected = index;
            this.filter.rating = this.stars[index];
            console.log("rating: ", this.filter.rating)

            //filter by rating
            this.filterByRating();
        },
    },
    mounted() {
        this.getProperties();
        this.getAllCities();
    }
}
</script>

<style scoped>

</style>