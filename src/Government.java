public enum Government {
		ANARCHY(false), ARISTOCRACY(false), BUREAUCRACY(true), CAPITALIST(true), COLONIALIST(true), 
		COMMUNIST(true), DEMOCRACY(true), FEDERALIST(true), FEUDAL(false), KLEPTOCRACY(true), 
		MERITOCRACY(true), DICTATORSHIP(false), MONARCHY(false), OLIGARCHY(false), PLUTOCRACY(false), 
		REPUBLIC(true), SOCIALIST(true), THEOCRACY(false), TOTALITARIAN(false), TRIBALIST(true);
		
		private boolean electionsAllowed;
		
		private Government(boolean electionsAllowed) {
			this.electionsAllowed = electionsAllowed;
		}
		
		public boolean getElectionsAllowed() {
			return electionsAllowed;
		}
		// no set needed (unless wanting to change election permission in a government style)
};