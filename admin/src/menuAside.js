import {
  mdiAccountCircle,
  mdiMonitor,
  mdiGithub,
  mdiLock,
  mdiAlertCircle,
  mdiSquareEditOutline,
  mdiTable,
  mdiViewList,
  mdiTelevisionGuide,
  mdiResponsive,
  mdiPalette,
  mdiReact,
  mdiHome, mdiAccountMultiple, mdiPlus,
  mdiAccount,
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
