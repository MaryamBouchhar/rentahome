<template>
  <div class="container w-11/12 mx-auto flex items-center justify-center flex-col">
    <!-- Section Title -->
    <div class="text-3xl font-bold mt-4 mb-2">
      <span>Contact </span>
      <span class="text-primary">Us</span>
    </div>

    <!-- Section Content -->
    <div class="hero flex items-center justify-center flex-col">
      <div class="hero-content flex-col items-center justify-center lg:flex-row-reverse">
        <div class="text-center">
          <div class="stats stats-vertical h-full w-[90%] sm:w-full  shadow w-full">
            <div class="stat flex flex-col items-start">
              <div class="stat-title">
                <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                  <path fill-rule="evenodd"
                        d="M2.94 6.412A2 2 0 002 8.108V16a2 2 0 002 2h12a2 2 0 002-2V8.108a2 2 0 00-.94-1.696l-6-3.75a2 2 0 00-2.12 0l-6 3.75zm2.615 2.423a1 1 0 10-1.11 1.664l5 3.333a1 1 0 001.11 0l5-3.333a1 1 0 00-1.11-1.664L10 11.798 5.555 8.835z"
                        clip-rule="evenodd"></path>
                </svg>
              </div>
              <div class="stat-value text-xl">contact@rentahome.com</div>
              <div class="stat-desc">
                Email us for any questions or inquiries.
              </div>
            </div>

            <div class="stat flex flex-col items-start">
              <div class="stat-title">
                <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                  <path
                      d="M2 3a1 1 0 011-1h2.153a1 1 0 01.986.836l.74 4.435a1 1 0 01-.54 1.06l-1.548.773a11.037 11.037 0 006.105 6.105l.774-1.548a1 1 0 011.059-.54l4.435.74a1 1 0 01.836.986V17a1 1 0 01-1 1h-2C7.82 18 2 12.18 2 5V3z"></path>
                </svg>
              </div>
              <div class="stat-value text-xl">+212600000000</div>
              <div class="stat-desc">
                Call us for any questions or inquiries.
              </div>
            </div>

            <div class="stat flex flex-col items-start">
              <div class="stat-title">
                <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                  <path fill-rule="evenodd"
                        d="M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z"
                        clip-rule="evenodd"></path>
                </svg>
              </div>
              <div class="stat-value text-xl">Taroudant, Morocco</div>
              <div class="stat-desc">
                Visit us for any questions or inquiries.
              </div>
            </div>
          </div>
        </div>
        <div class="card flex-shrink-0 w-full sm:w-3/5 shadow-xl bg-base-100">
          <form method="post" @submit.prevent="submit">
            <div class="card-body">
              <div class="form-control">
                <input type="text" placeholder="Your name" name="name" required class="input input-bordered"
                       v-model="name">
              </div>
              <div class="form-control">
                <input type="text" placeholder="Subject" name="subject" required class="input input-bordered"
                       v-model="subject">
              </div>
              <div class="form-control">
                <input type="email" placeholder="Your email" name="email" required class="input input-bordered"
                       v-model="email">
              </div>
              <div class="form-control">
              <textarea rows="3" placeholder="Your message" name="message" required
                        class="textarea h-24 textarea-bordered" v-model="message"></textarea>
              </div>
              <div class="form-control mt-3">
                <button class="btn btn-primary">submit</button>
              </div>
            </div>
          </form>
        </div>
      </div>

      <!-- Social Media -->
      <h1 class="font-bold text-3xl">Follow us on social media</h1>
      <div class="mt-4 mb-10 flex items-center justify-center flex-row gap-2">
        <a :href="linkedin" target="_blank" rel="noopener noreferrer">
          <i class='bx bxl-linkedin bx-sm transition delay-75 hover:text-primary hover:scale-125'></i>
        </a>

        <a :href="github" target="_blank" rel="noopener noreferrer">
          <i class='bx bxl-github bx-sm transition delay-75 hover:text-primary hover:scale-125'></i>
        </a>

        <a :href="instagram" target="_blank" rel="noopener noreferrer">
          <i class='bx bxl-instagram bx-sm transition delay-75 hover:text-primary hover:scale-125'></i>
        </a>

        <a :href="facebook" target="_blank" rel="noopener noreferrer">
          <i class='bx bxl-facebook bx-sm transition delay-75 hover:text-primary hover:scale-125'></i>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import 'boxicons'
export default {
  name: "Contact"
}
</script>

<style scoped>

</style>