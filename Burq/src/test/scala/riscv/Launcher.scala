// See LICENSE.txt for license details.
package riscv

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
	"ITD" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ITD(), manager) {
          (c) => new ITDTests(c)
        }
      },
	"CD" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new CD(), manager) {
          (c) => new CDTests(c)
        }
      },
	"Control" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Control(), manager) {
          (c) => new ControlTests(c)
        }
      },
	"AluControl" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new AluControl(), manager) {
          (c) => new AluControlTests(c)
        }
      },
	"alu" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new alu(), manager) {
          (c) => new alutests(c)
        }
      },
	"reg32" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new reg32(), manager) {
          (c) => new reg32tests(c)
        }
      },
	"Immediate" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Immediate(), manager) {
          (c) => new ImmediateTests(c)
        }
      },
	"conn" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new conn(), manager) {
          (c) => new conntests(c)
        }
      },
	"pc" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new pc(), manager) {
          (c) => new pctests(c)
        }
      },
	"instruction" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new instruction(), manager) {
          (c) => new instests(c)
        }
      },
	"sb_mux" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new sb_mux(), manager) {
          (c) => new sb_muxtests(c)
        }
      },
	"main_mem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new main_mem(), manager) {
          (c) => new maintests(c)
        }
      },

	"jalr" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new jalr(), manager) {
          (c) => new jalrtests(c)
        }
      }
	
)

  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

