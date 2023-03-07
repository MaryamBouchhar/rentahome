import {

  mdiMonitor,
  mdiLock,
  mdiViewList,
  mdiHome, mdiAccountMultiple, mdiPlus,
  mdiAccount,
  mdiFormatListText,
 mdiClipboardList,
  mdiCashRegister
} from "@mdi/js";

export default [
  {
    to: "/dashboard",
    icon: mdiMonitor,
    label: "Dashboard",
  },
  {

    label: "Properties",
    icon: mdiHome,
    menu: [
      {
        label: "View All",
        icon: mdiViewList,
        to: "/properties",
      },
      {
        label: "Add New",
        icon: mdiPlus,
        to: "/add-property",
      },
    ],
  },
  {

    label: "Reservation",
    icon: mdiFormatListText,
    menu: [
      {
        label: "Requests",
        icon: mdiClipboardList,
        to: "/booking_request",
      },
      {
        label: "Payements",
        icon:  mdiCashRegister ,
        to: "/manage_payements",
      },
    ],
  },

  {

    label: "Users",
    icon: mdiAccountMultiple,
    menu: [
      {
        label: "Clients",
        icon: mdiAccount,
        to: "/clients",
      },
      {
        label: "Admins",
        icon: mdiLock,
        to: "/admins",
      },
    ],
  },



];
