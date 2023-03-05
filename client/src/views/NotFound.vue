<template>
    <div
        class="flex flex-row items-center justify-center"
        :class="{ 'flex-row-reverse' : rtl }"
    >
        <svg :class="rtl ? 'svg-left' : 'svg-right'"
             width="380px" height="500px" viewBox="0 0 837 1045" version="1.1" xmlns="http://www.w3.org/2000/svg"
             xmlns:xlink="http://www.w3.org/1999/xlink">
            <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" sketch:type="MSPage">
                <path d="M353,9 L626.664028,170 L626.664028,487 L353,642 L79.3359724,487 L79.3359724,170 L353,9 Z"
                      id="Polygon-1" stroke="#007FB2" stroke-width="6" sketch:type="MSShapeGroup"></path>
                <path d="M78.5,529 L147,569.186414 L147,648.311216 L78.5,687 L10,648.311216 L10,569.186414 L78.5,529 Z"
                      id="Polygon-2" stroke="#EF4A5B" stroke-width="6" sketch:type="MSShapeGroup"></path>
                <path d="M773,186 L827,217.538705 L827,279.636651 L773,310 L719,279.636651 L719,217.538705 L773,186 Z"
                      id="Polygon-3" stroke="#795D9C" stroke-width="6" sketch:type="MSShapeGroup"></path>
                <path d="M639,529 L773,607.846761 L773,763.091627 L639,839 L505,763.091627 L505,607.846761 L639,529 Z"
                      id="Polygon-4" stroke="#F2773F" stroke-width="6" sketch:type="MSShapeGroup"></path>
                <path d="M281,801 L383,861.025276 L383,979.21169 L281,1037 L179,979.21169 L179,861.025276 L281,801 Z"
                      id="Polygon-5" stroke="#36B455" stroke-width="6" sketch:type="MSShapeGroup"></path>
            </g>
        </svg>
        <div
            :class="rtl ? 'text-right message-box-right' : 'text-left message-box-left'"
        >
            <h1
                class="mb-3 text-9xl font-bold text-secondary"
            >
                404
            </h1>
            <p
                class="mb-3 text-2xl font-semibold text-secondary"
            >
                {{ $t('notFound.page_not_found') }}
            </p>
            <div
                class="flex flex-row"
            >
                <router-link
                    onclick="history.back(-1)"
                    to
                    class="btn btn-primary btn-lg mt-3 mr-2"
                >
                    {{ $t('notFound.go_back') }}
                </router-link>
                <router-link
                    to="/"
                    class="btn btn-primary btn-lg mt-3 mr-2"
                >
                    {{ $t('notFound.go_home') }}
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: "NotFound",
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl)
        }
    },
}
</script>

<style scoped>
body {
    background-color: #2F3242;
}

.svg-right {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    margin-left: -400px;
}

.svg-left {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    /*margin-left: 50px;*/
}

.message-box-left {
    height: 200px;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -100px;
    margin-left: 50px;
    color: #FFF;
    font-family: Roboto;
    font-weight: 300;
}

.message-box-right {
    height: 200px;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -100px;
    margin-left: -400px;
    color: #FFF;
    font-family: Roboto;
    font-weight: 300;
}

#Polygon-1, #Polygon-2, #Polygon-3, #Polygon-4, #Polygon-4, #Polygon-5 {
    animation: float 1s infinite ease-in-out alternate;
}

#Polygon-2 {
    animation-delay: .2s;
}

#Polygon-3 {
    animation-delay: .4s;
}

#Polygon-4 {
    animation-delay: .6s;
}

#Polygon-5 {
    animation-delay: .8s;
}

@keyframes float {
    100% {
        transform: translateY(20px);
    }
}

@media (max-width: 640px) {
    .svg-right {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -250px;
        margin-left: -190px;
    }

    .svg-left {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -250px;
        margin-left: -190px;
    }

    .message-box-left {
        top: 50%;
        left: 50%;
        margin-top: -100px;
        margin-left: -190px;
        text-align: center;
    }

    .message-box-right {
        top: 50%;
        left: 50%;
        margin-top: -100px;
        margin-left: -190px;
        text-align: center;
    }
}
</style>