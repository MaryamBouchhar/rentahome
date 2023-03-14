<template>
  <div>
    <h1 class="w-full text-center font-bold text-2xl my-5">
      Properties
    </h1>
    <!-- TODO: Add filter options in sidebar -->
    <div class="flex flex-col lg:flex-row">
      <div class="filter-bar w-full lg:w-1/4 px-4">
        <!-- Search -->
        <div class="form-control mb-2">
          <div class="input-group">
            <input type="text" placeholder="Search…" class="input input-bordered"/>
            <button class="btn btn-square">
              <i class='bx bx-search bx-sm'></i>
            </button>
          </div>
        </div>

        <!-- Filter by type -->
        <label class="label font-bold">
          <span class="label-text">Filter by category</span>
        </label>
        <select class="select select-primary w-full max-w-xs">
          <option disabled selected>Filter by category</option>
          <option v-for="category in property_categories" :key="category">{{ category }}</option>
        </select>
        <!-- Filter by City -->
        <label class="label font-bold">
          <span class="label-text">Filter by city</span>
        </label>
        <select class="select select-primary w-full max-w-xs">
          <option disabled selected>Filter by city</option>
          <option v-for="city in cities" :key="city">{{ city }}</option>
        </select>

        <!-- Filter by price -->
        <label class="label font-bold">
          <span class="label-text">Filter by price</span>
        </label>
        <div class="flex justify-between gap-2">
          <input type="number" placeholder="Min" min="1" class="input input-bordered w-1/2"/>
          <input type="number" placeholder="Max" min="1" class="input input-bordered w-1/2"/>
        </div>

        <!-- Filter by rating -->
        <label class="label font-bold">
          <span class="label-text">Filter by rating</span>
        </label>
        <div class="rating">
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" checked/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
        </div>
      </div>
      <div class="flex flex-col w-full content-center mx-4">
        <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-2 flex justify-center mx-auto">
          <router-link v-for="property in properties" :key="property.id"
                       :to="{name: 'PropertyDetails', params: {id: property.id}}">
            <PropertyCard :property="property"/>
          </router-link>
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
      PROPERTY_API_BASE_URL: `http://localhost:8080/manage-properties/properties`,
      property_categories: [
        'House',
        'Apartment',
        'Condo',
        'Townhouse',
        'Villa',
        'Bungalow',
      ],
      cities: [
        'Toronto',
        'Ottawa',
        'Montreal',
        'Vancouver',
        'Calgary',
        'Edmonton',
        'Winnipeg',
      ],

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
  },
  mounted() {
    this.getProperties();

  }
}
/*const property_categories = ref([
  'House',
  'Apartment',
  'Condo',
  'Townhouse',
  'Villa',
  'Bungalow',
])

const cities = ref([
  'Toronto',
  'Ottawa',
  'Montreal',
  'Vancouver',
  'Calgary',
  'Edmonton',
  'Winnipeg',
])

const properties = ref([
  {
    id: 1,
    title: 'House in Toronto',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    category: 'House',
    city: 'Toronto',
    image: 'https://picsum.photos/500/300?random=1',
  },
  {
    id: 2,
    title: 'Apartment in Ottawa',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    category: 'Apartment',
    city: 'Ottawa',
    image: 'https://picsum.photos/500/300?random=2',
  },
  {
    id: 3,
    title: 'Condo in Montreal',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    category: 'Condo',
    city: 'Montreal',
    image: 'https://picsum.photos/500/300?random=3',
  },
  {
    id: 4,
    title: 'Townhouse in Vancouver',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    category: 'Townhouse',
    city: 'Vancouver',
    image: 'https://picsum.photos/500/300?random=4',
  },
  {
    id: 5,
    title: 'Villa in Calgary',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
    price: 1000,
    rating: 2,
    status: 'For Rent',
    category: 'Villa',
    city: 'Calgary',
    image: 'https://picsum.photos/500/300?random=5',
  },
  {
    id: 6,
    title: 'Bungalow in Edmonton',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    category: 'Bungalow',
    city: 'Edmonton',
    image: 'https://picsum.photos/500/300?random=6',
  },
]);*/
</script>

<style scoped>

</style>