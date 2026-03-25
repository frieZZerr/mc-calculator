const MAX_CALORIES = 2500

export const MACROS_CONFIG = {
  calories: {
    label: "Calories",
    unit: "kcal",
    color: "#f59e0b",
    max: MAX_CALORIES,
    getValue: (p) => p.caloriesPortion
  },
  fat: {
    label: "Fat",
    unit: "g",
    color: "#ef4444",
    max: MAX_CALORIES * 0.3 / 9,
    getValue: (p) => p.fatPortion
  },
  carbs: {
    label: "Carbs",
    unit: "g",
    color: "#3b82f6",
    max: MAX_CALORIES * 0.55 / 4,
    getValue: (p) => p.carbsPortion
  },
  protein: {
    label: "Protein",
    unit: "g",
    color: "#10b981",
    max: MAX_CALORIES * 0.15 / 4,
    getValue: (p) => p.proteinPortion
  }
}
