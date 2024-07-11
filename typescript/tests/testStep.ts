import { test } from "@playwright/test"

export function step(target: Function, context: ClassMethodDecoratorContext) {
  return function replacementMethod(...args: any) {
    const name = (context.name as string)
      .replace(/([a-z])([A-Z])/g, "$1 $2")
      .toLowerCase()
    return test.step(
      this.constructor.name + " - " + name,
      async () => await target.call(this, ...args)
    )
  }
}