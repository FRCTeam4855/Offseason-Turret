package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class FindZeroCommand extends Command {
  private final TurretSubsystem m_turretSubsystem;

  public FindZeroCommand(TurretSubsystem turretSubsystem) {
    m_turretSubsystem = turretSubsystem;
    addRequirements(m_turretSubsystem);
  }

  @Override
  public void initialize() {
    System.out.println("Finding Zero");
    m_turretSubsystem.setVelocity(50);
  }

  @Override
  public void execute() {
    // Keep moving until the limit switch is triggered
  }

  @Override
  public boolean isFinished() {
    if (Robot.magnet.get() == false) {
      m_turretSubsystem.setVelocity(0);
      m_turretSubsystem.resetEncoder();
      end(false);
    }
    return Robot.magnet.get() == true;
  }

  @Override
  public void end(boolean interrupted) {
    m_turretSubsystem.setVelocity(0);
  }
    
}
