package riscv

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
import chisel3.core.SInt

class Immediate extends Module{
	val io = IO(new Bundle{
		val instruction = Input(UInt(32.W))
		val pc = Input(UInt(32.W))
		val I_Type = Output(SInt(32.W))
		val S_Type = Output(SInt(32.W))
		val Sb_Type = Output(SInt(32.W))
		val Uj_Type = Output(SInt(32.W))
		val U_Type = Output(SInt(32.W))

  })

		val full_imm = io.instruction(31,20)
		io.I_Type := Cat(Fill(20, full_imm(11)), full_imm).asSInt

		val S_lower = io.instruction(11,7)
		val S_upper = io.instruction(31,25)
		val full_imm1 = Cat(S_upper, S_lower)
		io.S_Type := Cat(Fill(20, full_imm1(11)), full_imm1).asSInt

		val sb_11th = io.instruction(7)
		val sb_lower = io.instruction(11,8)
		val sb_upper = io.instruction(30,25)
		val sb_12th = io.instruction(31)
		val full_imm2 = Cat(sb_12th, sb_11th, sb_upper, sb_lower, "b0".U)
		val sb_imm_32 = Cat(Fill(19, full_imm2(12)), full_imm2).asSInt
		io.Sb_Type := sb_imm_32 + io.pc.asSInt


		val uj_upper = io.instruction(19,12)
		val uj_11th = io.instruction(20)
		val uj_lower = io.instruction(30,21)
		val uj_20th = io.instruction(31)
		val full_imm3 = Cat(uj_20th,uj_upper,uj_11th,uj_lower,  "b0".U)
		val uj_imm_32 = Cat(Fill(11, full_imm3(20)), full_imm3).asSInt
		io.Uj_Type := uj_imm_32 + io.pc.asSInt
		

		val u_imm = io.instruction(31,12)
		val u_imm1 = Cat(Fill(12, u_imm(19)), u_imm).asSInt
		val U_shift = u_imm1 << 12.U
		io.U_Type := U_shift.asSInt

		


}
